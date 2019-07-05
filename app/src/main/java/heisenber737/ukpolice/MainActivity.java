package heisenber737.ukpolice;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;


import java.util.ArrayList;

import recyclerview.crimesAtLocation;
import recyclerview.forces;
import recyclerview.specificForces;
import recyclerview.seniorForces;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    FragmentManager fragmentManager1=getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_icon);

        drawerLayout=findViewById(R.id.intro);
        navigationView=findViewById(R.id.navigation);

        if(findViewById(R.id.intro)!=null)
        {
            if(savedInstanceState!=null)
                return;

        }
        FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
        fragmentTransaction1.add(R.id.MainContent,new intro_fragment(),null).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment=new intro_fragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                switch(menuItem.getItemId())
                {
                    case R.id.forces:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportFragmentManager().popBackStack();
                        toolbar.setTitle("Forces");
                        fragment=new forces();
                        break;
                    case R.id.force_senior:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportFragmentManager().popBackStack();
                        toolbar.setTitle("Force Senior Officers ");
                        fragment=new seniorForces();
                        break;
                    case R.id.crimes_location:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportFragmentManager().popBackStack();
                        toolbar.setTitle("Crimes At Location");
                        fragment=new crimesAtLocation();
                        break;
                    case R.id.favourites:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        toolbar.setTitle("Favourites");
                        break;
                    case R.id.specific_force:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportFragmentManager().popBackStack();
                        toolbar.setTitle("Specific Forces");
                        fragment=new specificForces();
                        break;

                }
                fragmentTransaction.replace(R.id.MainContent,fragment,null).commit();

                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_icon, menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        return true;
    }
}
