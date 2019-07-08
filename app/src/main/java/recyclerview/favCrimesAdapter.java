package recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import heisenber737.ukpolice.R;
import heisenber737.ukpolice.favCrimes;

public class favCrimesAdapter extends RecyclerView.Adapter<favCrimesAdapter.favCrimesViewHolder> {

    Context context;
    Cursor cursor;

    public favCrimesAdapter(Context context, Cursor cursor)
    {
        this.context=context;
        this.cursor=cursor;
    }

    @NonNull
    @Override
    public favCrimesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row_item_crimes,viewGroup,false);
        favCrimesViewHolder viewHolder=new favCrimesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull favCrimesViewHolder viewHolder, int i) {
        if(!cursor.moveToPosition(i))
        {
            return;
        }
        String Category=cursor.getString(cursor.getColumnIndex(favCrimes.favCrimesHelper.CATEGORY));
        String Location=cursor.getString(cursor.getColumnIndex(favCrimes.favCrimesHelper.LOCATION_TYPE));
        String Outcome=cursor.getString(cursor.getColumnIndex(favCrimes.favCrimesHelper.OUTCOME));
        String Month=cursor.getString(cursor.getColumnIndex(favCrimes.favCrimesHelper.MONTH)); //logcat shows
        viewHolder.outcome.setText(Outcome);
        viewHolder.location.setText(Location);
        viewHolder.category.setText(Category);
        viewHolder.month.setText(Month);

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor)
    {
        if(cursor!=null)
            cursor.close();
        cursor=newCursor;
        if(newCursor!=null)
            notifyDataSetChanged();
    }

    public class favCrimesViewHolder extends RecyclerView.ViewHolder{

        TextView category,location,outcome,month;

        public favCrimesViewHolder(@NonNull View itemView) {
            super(itemView);
            category=itemView.findViewById(R.id.category);
            location=itemView.findViewById(R.id.locationtype);
            outcome=itemView.findViewById(R.id.outcome_status);
            month=itemView.findViewById(R.id.yyyymm);
        }
    }
}
