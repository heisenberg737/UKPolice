package heisenber737.ukpolice;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import recyclerview.favCrimesAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Favourites extends Fragment {
    SearchView searchView;
    RecyclerView recyclerView;
    favCrimesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    favCrimes.favCrimesHelper helper;
    SQLiteDatabase db;


    public Favourites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favourites, container, false);
        helper=new favCrimes.favCrimesHelper(getContext());
        db=helper.getWritableDatabase();
        searchView=view.findViewById(R.id.favourites_search);
        searchView.setQueryHint("Search by Outcome");
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView=view.findViewById(R.id.favourites_list);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new favCrimesAdapter(getContext(),getCursor());
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s=s.toLowerCase();
                Cursor cursor=searchDatabase(s);
                if(cursor!=null)
                {
                    adapter.swapCursor(cursor);
                }
                return true;
            }
        });

        return view;
    }

     Cursor getCursor()
    {
          return db.query(favCrimes.favCrimesHelper.tableName,null,null,null,null,null,null);
    }

    Cursor searchDatabase(String search)
    {
        SQLiteDatabase db=helper.getReadableDatabase();
        String selectQuery="SELECT "+favCrimes.favCrimesHelper.CATEGORY+","+favCrimes.favCrimesHelper.MONTH+","+favCrimes.favCrimesHelper.LOCATION_TYPE+","+favCrimes.favCrimesHelper.OUTCOME+" FROM "+favCrimes.favCrimesHelper.tableName+" WHERE "+favCrimes.favCrimesHelper.OUTCOME+" LIKE '%"+search+"%'";
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor==null)
            return null;
        else if(!cursor.moveToFirst())
        {
            cursor.close();
            return  null;
        }
        return cursor;
    }

}
