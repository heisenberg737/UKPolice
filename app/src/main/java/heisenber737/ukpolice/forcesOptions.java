package heisenber737.ukpolice;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import recyclerview.seniorForces;
import recyclerview.specificForces;


/**
 * A simple {@link Fragment} subclass.
 */
public class forcesOptions extends Fragment {
    Button description,seniorforces;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String mess;
    TextView textView;


    public forcesOptions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_forces_options, container, false);
        description=view.findViewById(R.id.description);
        textView=view.findViewById(R.id.mytext);
        seniorforces=view.findViewById(R.id.senior_officers);
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        mess=getArguments().getString("place");
        textView.setText(mess);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                specificForces fragment=new specificForces();
                Bundle bundle=new Bundle();
                bundle.putString("id",mess);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.MainContent,fragment).addToBackStack(null).commit();
            }
        });

        seniorforces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seniorForces force=new seniorForces();
                Bundle bundle=new Bundle();
                bundle.putString("id",mess);
                force.setArguments(bundle);
                fragmentTransaction.replace(R.id.MainContent,force).addToBackStack(null).commit();
            }
        });



        return view;
    }



    //    @Override
//    public void onClick(View v) {
//
//        switch (v.getId())
//        {
//            case R.id.description:
//                FragmentManager fragmentManager=getFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.MainContent,new specificForces()).addToBackStack(null).commit();
//                break;
//            case R.id.senior_officers:
//                FragmentManager fragmentManager1=getFragmentManager();
//                FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
//                fragmentTransaction1.replace(R.id.MainContent,new seniorForces()).addToBackStack(null).commit();
//                break;
//
//
//        }
//
//    }
}
