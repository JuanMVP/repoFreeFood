package com.example.freefoodapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.freefoodapp.R;
import com.example.freefoodapp.fragments.RecetasFavoritasFragment;
import com.example.freefoodapp.fragments.RecetasFragment;
import com.example.freefoodapp.fragments.RestaurantesFragment;
import com.example.freefoodapp.fragments.dummy.DummyContent;
import com.example.freefoodapp.interfaces.OnListFragmentRestaurantListener;
import com.example.freefoodapp.models.Restaurant;
import com.example.freefoodapp.util.Util;

import java.io.Serializable;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnListFragmentRestaurantListener, RecetasFragment.OnListFragmentInteractionListener {

     FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
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
        //color para iconos
        navigationView.setItemIconTintList(null);




        View headerView = navigationView.getHeaderView(0);

        ImageView iv = headerView.findViewById(R.id.picture);
        TextView name = headerView.findViewById(R.id.userName);
        TextView email = headerView.findViewById(R.id.emailUser);

        name.setText(Util.getNombreUser(DashboardActivity.this) + Util.getNombreUser(DashboardActivity.this));
        email.setText(Util.getEmailUser(DashboardActivity.this));
        Glide.with(this).load(Util.getPhotoUser(DashboardActivity.this)).apply(RequestOptions.circleCropTransform()).into(iv);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new RestaurantesFragment()).commit();
        fab.hide();

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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_login) {
            startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
        } else if (id == R.id.nav_restaurant) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new RestaurantesFragment()).commit();
            fab.hide();


        } else if (id == R.id.nav_recetas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new RecetasFragment()).commit();
            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DashboardActivity.this,AddRecipeActivity.class));
                }
            });


        } else if (id == R.id.nav_recetas_favoritas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, new RecetasFavoritasFragment()).commit();
            fab.hide();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void OnClickRestaurant(Restaurant restaurant) {
        Intent details = new Intent(DashboardActivity.this,RestaurantDetailsActivity.class);
        details.putExtra("restaurant_id",restaurant.getId());
        details.putExtra("restaurant_name",restaurant.getName());
        details.putExtra("restaurant_address",restaurant.getAddress());
        details.putExtra("restaurant_intolerance", (Serializable) restaurant.getIntolerance());
        details.putExtra("restaurant_timetable",restaurant.getTimetable());
        details.putExtra("restaurant_loc",restaurant.getLoc());
        details.putExtra("restaurant_description",restaurant.getDescription());
        startActivity(details);





    }
}
