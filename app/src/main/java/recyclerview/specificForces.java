package recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;



import heisenber737.ukpolice.MySingleton;
import heisenber737.ukpolice.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class specificForces extends Fragment {
    String forces_url="https://data.police.uk/api/forces/",des,message;
    TextView id,name,url,telephone,description;


    public specificForces() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_specific_forces, container, false);
        id=view.findViewById(R.id.text1);
        name=view.findViewById(R.id.textview4);
        url=view.findViewById(R.id.textview2);
        telephone=view.findViewById(R.id.textview6);
        description=view.findViewById(R.id.textview8);
        message=getArguments().getString("id");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, forces_url+message, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    id.setText(response.getString("id"));
                    name.setText(response.getString("name"));
                    url.setText(response.getString("url"));
                    telephone.setText(response.getString("telephone"));
                    if(response.isNull("description"))
                    {
                        description.setText("Description Not Available");
                    }
                    else
                        description.setText(response.getString("description"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(),"Something went wrong.",Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        });


        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

        return view;
    }


}
