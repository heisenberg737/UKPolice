package recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import heisenber737.ukpolice.R;
import heisenber737.ukpolice.crimesClass;

public class crimesAtLocationAdapter extends RecyclerView.Adapter<crimesAtLocationAdapter.CrimesViewHolder> {

    ArrayList<crimesClass> arrayList=new ArrayList<>();
    public  crimesAtLocationAdapter(ArrayList<crimesClass> arrayList)
    {
        this.arrayList=arrayList;
    }


    @NonNull
    @Override
    public CrimesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row_item_crimes,viewGroup,false);
        CrimesViewHolder crimesViewHolder=new CrimesViewHolder(view);
        return crimesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CrimesViewHolder viewHolder, int i) {
        viewHolder.category.setText(arrayList.get(i).getCategory());
        viewHolder.location.setText(arrayList.get(i).getLocation());
        viewHolder.outcome.setText(arrayList.get(i).getOutcome());
        viewHolder.month.setText(arrayList.get(i).getMonth());

    }

    public void swapData(ArrayList<crimesClass> newList)
    {
        arrayList=newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CrimesViewHolder extends RecyclerView.ViewHolder{
        TextView category,location,outcome,month;

        public CrimesViewHolder(@NonNull View itemView) {
            super(itemView);

            category=itemView.findViewById(R.id.category);
            location=itemView.findViewById(R.id.locationtype);
            outcome=itemView.findViewById(R.id.outcome_status);
            month=itemView.findViewById(R.id.yyyymm);

        }

    }

    public void setFilter(ArrayList<crimesClass> newList)
    {
        arrayList=new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
