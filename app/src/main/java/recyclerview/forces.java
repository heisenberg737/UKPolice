package recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import heisenber737.ukpolice.MySingleton;
import heisenber737.ukpolice.R;
import heisenber737.ukpolice.forcesOptions;
import heisenber737.ukpolice.forces_cl;
import heisenber737.ukpolice.onItemClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class forces extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    forces_adapter adapter;
    ArrayList<forces_cl> arrayList=new ArrayList<>();
    String forces_url="https://data.police.uk/api/forces";
    SearchView searchView;





    public forces() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_forces, container, false);
        recyclerView=view.findViewById(R.id.forceslist);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        arrayList.clear();
        searchView=view.findViewById(R.id.search);
        searchView.setQueryHint("Search by Name");
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, forces_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++)
                {
                    try {

                        JSONObject jsonObject=response.getJSONObject(i);
                        forces_cl forcesCl=new forces_cl(jsonObject.getString("id"),jsonObject.getString("name"));
                        arrayList.add(forcesCl);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.swapData(arrayList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(),"Something went wrong. Check your internet connection or make sure you entered correct values.",Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);

        adapter=new forces_adapter(arrayList,listener);
        recyclerView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s=s.toLowerCase();
                ArrayList<forces_cl> newList=new ArrayList<>();
                for(forces_cl force:arrayList)
                {
                    String name=force.getName().toLowerCase();
                    if(name.contains(s))
                    {
                        newList.add(force);
                    }
                }
                adapter.setFilter(newList);
                return true;
            }
        });


        return view;
    }

    onItemClickListener listener=new onItemClickListener() {
        @Override
        public void onClick(int position, String str) {
            forcesOptions forces=new forcesOptions();
            Bundle bundle=new Bundle();
            bundle.putString("place",str);
            forces.setArguments(bundle);
            FragmentManager fragmentManager=getFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.MainContent,forces).addToBackStack(null).commit();
        }
    };




}
