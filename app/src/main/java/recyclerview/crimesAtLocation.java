package recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import heisenber737.ukpolice.MainActivity;
import heisenber737.ukpolice.MySingleton;
import heisenber737.ukpolice.R;
import heisenber737.ukpolice.crimesClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class crimesAtLocation extends Fragment implements SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<crimesClass> arrayList=new ArrayList<>();
    crimesAtLocationAdapter adapter;
    String forces_url,lat,lon,dat,outcome;
    EditText latitude,longitude,date;
    Button button;


    public crimesAtLocation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_crimes_at_location, container, false);
        recyclerView=view.findViewById(R.id.crimes_location);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        longitude=view.findViewById(R.id.longitude);
        latitude=view.findViewById(R.id.latitude);
        date=view.findViewById(R.id.yearandmonth);
        button=view.findViewById(R.id.show);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                if(v.getId()==R.id.show)
                {lat = latitude.getText().toString();
                lon = longitude.getText().toString();
                dat = date.getText().toString();
                forces_url = "https://data.police.uk/api/crimes-at-location?date=" + dat + "&lat=" + lat + "&lng=" + lon;
                    Log.d("Crimes",""+forces_url);


                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, forces_url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                if(jsonObject.isNull("outcome_status"))
                                {
                                    outcome="Outcome Not Available";
                                }
                                else
                                {
                                    JSONObject jsonObject1=jsonObject.getJSONObject("outcome_status");
                                    outcome=jsonObject1.getString("category");
                                }
                                crimesClass crime = new crimesClass(jsonObject.getString("category"), jsonObject.getString("location_type"), jsonObject.getString("month"),outcome);
                                arrayList.add(crime);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.swapData(arrayList);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                });

                MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
            }

            }
        });

        adapter=new crimesAtLocationAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        s=s.toLowerCase();
        ArrayList<crimesClass> newList=new ArrayList<>();
        for(crimesClass crime:arrayList)
        {
            String category=crime.getCategory().toLowerCase();
            if(category.contains(s))
            {
                newList.add(crime);
            }
        }
        adapter.setFilter(newList);
        return true;
    }
}
