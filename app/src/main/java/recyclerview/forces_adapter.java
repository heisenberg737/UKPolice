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

public class forces_adapter extends RecyclerView.Adapter<forces_adapter.ForcesViewHolder> {

     ArrayList<forces_cl> forces_clArrayList=new ArrayList<>();

  public forces_adapter(ArrayList<forces_cl> arrayList)
  {
      this.forces_clArrayList=arrayList;
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

    @Override
    public int getItemCount() {
        return forces_clArrayList.size();
    }
    public static class ForcesViewHolder extends RecyclerView.ViewHolder{
        TextView ID,Name;

        public ForcesViewHolder(@NonNull View itemView) {
            super(itemView);
            ID=itemView.findViewById(R.id.ID);
            Name=itemView.findViewById(R.id.name);
        }
    }
}
