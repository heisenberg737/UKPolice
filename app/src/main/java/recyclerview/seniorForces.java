package recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import heisenber737.ukpolice.forces_cl;

/**
 * A simple {@link Fragment} subclass.
 */
public class seniorForces extends Fragment {
    String forces_url="https://data.police.uk/api/forces/leicestershire/people";
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    seniorForcesAdapter adapter;
    ArrayList<forces_cl> arrayList=new ArrayList<>();


    public seniorForces() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_senior_forces, container, false);
        recyclerView=view.findViewById(R.id.senior_forces_list);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, forces_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        forces_cl forcesCl=new forces_cl(jsonObject.getString("rank"),jsonObject.getString("name"));
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
                Toast.makeText(getContext(),"Something went wrong..",Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
        adapter=new seniorForcesAdapter(arrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

}