package recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import heisenber737.ukpolice.R;
import heisenber737.ukpolice.forces_cl;

public class forces_adapter extends RecyclerView.Adapter<forces_adapter.ForcesViewHolder> implements Filterable {

     ArrayList<forces_cl> forces_clArrayList=new ArrayList<>();
     ArrayList<forces_cl> forces_clArrayListfull;

  public forces_adapter(ArrayList<forces_cl> arrayList)
  {
      this.forces_clArrayList=arrayList;
      forces_clArrayListfull=new ArrayList<>(arrayList);
  }
    @Override
    public ForcesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_layout_forces,viewGroup,false);
        ForcesViewHolder forcesViewHolder=new ForcesViewHolder(view);

        return forcesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForcesViewHolder viewHolder, int i) {


        viewHolder.ID.setText(forces_clArrayList.get(i).getID());
        viewHolder.Name.setText(forces_clArrayList.get(i).getName());


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

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<forces_cl> filteredList=new ArrayList<>();

            if(constraint==null||constraint.length()==0)
            {
                filteredList.addAll(forces_clArrayListfull);
            }
            else
            {
                String filterPattern=constraint.toString().toLowerCase().trim();
                for(forces_cl force:forces_clArrayListfull)
                {
                    if(force.getName().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(force);
                    }
                }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            forces_clArrayList.clear();
            forces_clArrayList.addAll((Collection<? extends forces_cl>) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ForcesViewHolder extends RecyclerView.ViewHolder{
        TextView ID,Name;

        public ForcesViewHolder(@NonNull View itemView) {
            super(itemView);
            ID=itemView.findViewById(R.id.ID);
            Name=itemView.findViewById(R.id.name);
        }
    }

//    public void setFilter(ArrayList<forces_cl> newList)
//    {
//        forces_clArrayList=new ArrayList<>();
//        forces_clArrayList.addAll(newList);
//        notifyDataSetChanged();
//    }
}
