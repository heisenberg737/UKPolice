package recyclerview;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

import heisenber737.ukpolice.MySingleton;
import heisenber737.ukpolice.R;
import heisenber737.ukpolice.crimesClass;
import heisenber737.ukpolice.favCrimes;


/**
 * A simple {@link Fragment} subclass.
 */
public class crimesAtLocation extends Fragment  {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<crimesClass> arrayList=new ArrayList<>();
    crimesAtLocationAdapter adapter;
    String forces_url,lat,lon,dat,outcome;
    EditText latitude,longitude,date;
    Button button;
    favCrimes favourites;


    public crimesAtLocation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_crimes_at_location, container, false);
        recyclerView=view.findViewById(R.id.crimes_location);
        layoutManager=new LinearLayoutManager(getContext());
        favourites=new favCrimes(getContext());
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
                                crimesClass crime = new crimesClass(jsonObject.getString("category"),jsonObject.getString("month"), jsonObject.getString("location_type"),outcome);
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

                        Toast.makeText(getContext(), "Something went wrong. Check your internet connection or make sure you entered correct values.", Toast.LENGTH_LONG).show();
                        error.printStackTrace();

                    }
                });

                MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
            }

            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                 //insertion done here. insertData function present in favCrimes class. Insertion is failing
                long id=favourites.insertData(arrayList.get(viewHolder.getAdapterPosition()).getCategory(),arrayList.get(viewHolder.getAdapterPosition()).getMonth(),arrayList.get(viewHolder.getAdapterPosition()).getLocation(),arrayList.get(viewHolder.getAdapterPosition()).getOutcome());
                Log.d("Favourites",arrayList.get(viewHolder.getAdapterPosition()).getMonth());
//                Log.d("Favourites",""+id);
                if(id>0)
                {
                    Toast.makeText(getContext(),"Added to Favourites",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext(),"Insertion Failed",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX/15, dY, actionState, isCurrentlyActive);

            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        adapter=new crimesAtLocationAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }





}
