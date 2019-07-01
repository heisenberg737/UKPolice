package heisenber737.ukpolice;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetForces {

    Context context;
    ArrayList<forces_cl> arrayList=new ArrayList<>();
    String forces_url="https://data.police.uk/api/forces";

    public GetForces(Context context)
    {
        this.context=context;

//        if(i==1)
//            this.forces_url=;
//        else if(i==2)
//            this.forces_url="https://data.police.uk/api/forces/leicestershire";
    }

    public ArrayList<forces_cl> getList()
    {
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

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,"Something went wrong..",Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);

        return arrayList;
    }
}
