package com.example.sayed.soufra.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sayed.soufra.R;
import com.example.sayed.soufra.helper.HelperMethod;
import com.example.sayed.soufra.ui.fragments.ContainerFragment;
import com.example.sayed.soufra.ui.fragments.FoodOrderFragment;
import com.example.sayed.soufra.ui.fragments.LoginFragment;
import com.example.sayed.soufra.ui.fragments.MyOrdersContainerFragment;
import com.example.sayed.soufra.ui.fragments.MyproductsFragment;
import com.example.sayed.soufra.ui.fragments.RestaurantOrderContainerFragment;
import com.example.sayed.soufra.ui.fragments.SplashFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            id = extra.getInt("id");

        } else {
            id = 2;
        }
        Toast.makeText(this, "id = " + id, Toast.LENGTH_SHORT).show();

        if (id == 1) {
            FoodOrderFragment foodOrderFragment = new FoodOrderFragment();
            HelperMethod.FragmentManager(foodOrderFragment, getSupportFragmentManager(), R.id.nav_container, null, null);
        } else {
            Toast.makeText(this, "not success" + id, Toast.LENGTH_SHORT).show();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu=navigationView.getMenu();

        if(id==1){
            menu.findItem(R.id.nav_myproducts).setVisible(false);
            menu.findItem(R.id.nav_commition).setVisible(false);
            menu.findItem(R.id.nav_required).setVisible(false);
            menu.findItem(R.id.nav_offers).setVisible(false);

        }else if(id!=1){
            menu.findItem(R.id.nav_myorders).setVisible(false);
            menu.findItem(R.id.nav_notifications).setVisible(false);
            menu.findItem(R.id.nav_neworder).setVisible(false);

        }

        navigationView.getHeaderView(0).findViewById(R.id.home_btnimg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == 1) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("key_id", 5);
                    LoginFragment loginFragment = new LoginFragment();
                    loginFragment.setArguments(bundle);
                    HelperMethod.FragmentManager(loginFragment, getSupportFragmentManager(), R.id.nav_container, null, null);
                    Toast.makeText(HomeActivity.this, "client" + id, Toast.LENGTH_SHORT).show();
                } else {
                    HelperMethod.FragmentManager(new LoginFragment(), getSupportFragmentManager(), R.id.nav_container, null, null);
                }


            }
        });

        // ContainerFragment fragment1=new ContainerFragment();
        // HelperMethod.FragmentManager(fragment1,getSupportFragmentManager(),R.id.nav_container,null,null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            // Handle the camera action
        } else if (id == R.id.nav_myproducts) {
          //  Intent intent=new Intent(this,new MyOrdersContainerFragment().getClass());
          //  startActivity(intent);
            HelperMethod.FragmentManager(new MyproductsFragment(),getSupportFragmentManager(),R.id.nav_container,null,null);

        }else if (id==R.id.nav_myorders){
            HelperMethod.FragmentManager(new MyOrdersContainerFragment(),getSupportFragmentManager(),R.id.nav_container,null,null);

        }
        else if (id == R.id.nav_required) {
            HelperMethod.FragmentManager(new RestaurantOrderContainerFragment(),getSupportFragmentManager(),R.id.nav_container,null,null);


        } else if (id == R.id.nav_commition) {

        } else if (id == R.id.nav_offers) {

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
