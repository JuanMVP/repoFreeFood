package com.example.freefoodapp.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freefoodapp.R;
import com.example.freefoodapp.models.Restaurant;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RestaurantService;
import com.example.freefoodapp.util.UtilToken;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private String token,loc,restaurant_id,restaurant_name,restaurant_address,restaurant_intolerance,restaurant_description,restaurant_timetable;
    private TextView nombreRestaurante,direccionRestaurante,horarioRestaurante,intoleranciasRestaurantes,restauranteDescripcion;
    private ViewPager fotosPage;
    private Restaurant restaurante;
    private ImageView imagenDetalleRest;
    private MapView mapViewDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            restaurant_id = extras.getString("restaurant_id");
            restaurant_name = extras.getString("restaurant_name");
            restaurant_address = extras.getString("restaurant_address");
            restaurant_intolerance = extras.getString("restaurant_intolerance");
            restaurant_description = extras.getString("restaurant_description");
            restaurant_timetable = extras.getString("restaurant_timetable");
            loc = extras.getString("restaurant_loc");
        }






        token = UtilToken.getToken(RestaurantDetailsActivity.this);
        nombreRestaurante = findViewById(R.id.nombreReceta);
        direccionRestaurante = findViewById(R.id.ingredientesRecetaDetalle);
        horarioRestaurante = findViewById(R.id.comensalesEditReceta);
        intoleranciasRestaurantes = findViewById(R.id.intoleranciasDetailsRestaurant);
        imagenDetalleRest = findViewById(R.id.imagenDetalle);
        restauranteDescripcion = findViewById(R.id.descripcionRestaurante);
        mapViewDetalle = findViewById(R.id.mapView);
        mapViewDetalle.onCreate(savedInstanceState);
        mapViewDetalle.getMapAsync(this);





        final RestaurantService restaurantService = ServiceGenerator.createService(RestaurantService.class);
        Call<Restaurant> callOneRestaurant = restaurantService.getOneRestaurant(restaurant_id);
        callOneRestaurant.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant>response) {
                if(response.isSuccessful()){

                    restaurante = response.body();
                    //if(restaurante.getPicture() != null){
                        Glide.with(RestaurantDetailsActivity.this).load(restaurante.getPicture()).into(imagenDetalleRest);
                   /* }else{
                        Glide.with(RestaurantDetailsActivity.this).load("https://u.tfstatic.com/restaurant_photos/665/68665/169/612/happy-day-vegetariano-vista-sala-a902a.jpg").into(imagenDetalle);
                    }*/


                    nombreRestaurante.setText(restaurante.getName());
                    direccionRestaurante.setText(restaurante.getAddress());
                    intoleranciasRestaurantes.setText(restaurante.getIntolerance().get(0).getName());
                    horarioRestaurante.setText(restaurante.getTimetable());
                    restauranteDescripcion.setText(restaurante.getDescription());
                    loc = restaurante.getLoc();

                }else{


                    Toast.makeText(RestaurantDetailsActivity.this, "Error al ver el Restaurante", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(RestaurantDetailsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

            }
        });


    }




    @Override
    protected void onResume() {
        super.onResume();
        mapViewDetalle.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapViewDetalle.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapViewDetalle.onStop();
    }

    @Override
    protected void onPause() {
        mapViewDetalle.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapViewDetalle.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapViewDetalle.onLowMemory();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        loc = restaurante.getLoc();

        String[] latlong = loc.split(",");

        double lat = Double.parseDouble(latlong[0]);
        Double lon = Double.parseDouble(latlong[1]);

        LatLng latLng = new LatLng(lat, lon);
        googleMap.setMinZoomPreference(12);


            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(String.valueOf(nombreRestaurante))
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_map_house))
            );



        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
    }
}
