package recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import heisenber737.ukpolice.R;
import heisenber737.ukpolice.forcesOptions;
import heisenber737.ukpolice.forces_cl;
import heisenber737.ukpolice.onItemClickListener;

public class forces_adapter extends RecyclerView.Adapter<forces_adapter.ForcesViewHolder>  {

     private ArrayList<forces_cl> forces_clArrayList;
     onItemClickListener onItemClickListener;




  public forces_adapter(ArrayList<forces_cl> arrayList,onItemClickListener itemClickListener)
  {
      this.forces_clArrayList=arrayList;
      this.onItemClickListener=itemClickListener;

  }
    @Override
    public ForcesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_layout_forces,viewGroup,false);
        ForcesViewHolder forcesViewHolder=new ForcesViewHolder(view,onItemClickListener);
        return forcesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForcesViewHolder viewHolder, int i) {

         final int j=i;
        viewHolder.ID.setText(forces_clArrayList.get(i).getID());
        viewHolder.Name.setText(forces_clArrayList.get(i).getName());
        forcesOptions forces=new forcesOptions();
        Bundle bundle=new Bundle();
        bundle.putString("place",forces_clArrayList.get(j).getID());
        forces.setArguments(bundle);


    }

    public void swapData(ArrayList<forces_cl> newList)
    {
        forces_clArrayList=newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return forces_clArrayList.size();
    }



    public class ForcesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView ID,Name;
        onItemClickListener listener;

        public ForcesViewHolder(@NonNull View itemView,onItemClickListener itemClickListener) {
            super(itemView);
            ID=itemView.findViewById(R.id.ID);
            Name=itemView.findViewById(R.id.name);
            listener=itemClickListener;
            Name.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           listener.onClick(getAdapterPosition(),forces_clArrayList.get(getAdapterPosition()).getID());
        }
    }

    public void setFilter(ArrayList<forces_cl> newList)
    {
        forces_clArrayList=new ArrayList<>();
        forces_clArrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
