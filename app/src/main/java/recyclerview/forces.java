package recyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import heisenber737.ukpolice.GetForces;
import heisenber737.ukpolice.R;
import heisenber737.ukpolice.forces_cl;


/**
 * A simple {@link Fragment} subclass.
 */
public class forces extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<forces_cl> arrayList=new ArrayList<>();


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
        GetForces getForces=new GetForces(getActivity());
        arrayList=getForces.getList();
        adapter=new forces_adapter(arrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }


}
