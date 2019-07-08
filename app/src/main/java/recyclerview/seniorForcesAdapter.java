package recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import heisenber737.ukpolice.R;
import heisenber737.ukpolice.forces_cl;

public class seniorForcesAdapter extends RecyclerView.Adapter<seniorForcesAdapter.MyViewHolder> {
    ArrayList<forces_cl>  arrayList=new ArrayList<>();


    public seniorForcesAdapter(ArrayList<forces_cl> arrayList)
    {
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_layout_forces,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.rank.setText(arrayList.get(i).getID());
        viewHolder.name.setText(arrayList.get(i).getName());

    }

    public void swapData(ArrayList<forces_cl> newList)
    {
        arrayList=newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,rank;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            rank=itemView.findViewById(R.id.ID);
        }
    }

    public void setFilter(ArrayList<forces_cl> newList)
    {
        arrayList=new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }


}
