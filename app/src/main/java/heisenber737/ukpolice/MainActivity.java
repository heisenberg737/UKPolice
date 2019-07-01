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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import recyclerview.forces;

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
                        toolbar.setTitle("Force Senior Officers ");
                        break;
                    case R.id.crimes_location:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        toolbar.setTitle("Crimes At Location");
                        break;
                    case R.id.crimes_no_location:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        toolbar.setTitle("Crimes with no Location");
                        break;
                    case R.id.specific_force:
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        toolbar.setTitle("Specific Forces");
                        break;

                }
                fragmentTransaction.replace(R.id.MainContent,fragment,null).commit();

                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.intro) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
