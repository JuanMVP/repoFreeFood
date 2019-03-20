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
import com.example.freefoodapp.models.OneResponseContainer;
import com.example.freefoodapp.models.Restaurant;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RestaurantService;
import com.example.freefoodapp.util.UtilToken;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailsActivity extends AppCompatActivity implements OnMapReadyCallback{
    private String token;
    private TextView nombreRestaurante,direccionRestaurante,horarioRestaurante,intoleranciasRestaurantes;
    private MapView mapView;
    private ViewPager fotosPage;
    private Restaurant restaurante;
    private ImageView imagenDetalleRest;
    private List<String> loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        final Intent i = getIntent();
        String idRestaurante = i.getStringExtra("id");

        token = UtilToken.getToken(RestaurantDetailsActivity.this);
        nombreRestaurante = findViewById(R.id.nombreReceta);
        direccionRestaurante = findViewById(R.id.ingredientesRecetaDetalle);
        horarioRestaurante = findViewById(R.id.comensalesReceta);
        intoleranciasRestaurantes = findViewById(R.id.intoleranciasDetailsRestaurant);
        imagenDetalleRest = findViewById(R.id.imagenDetalle);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync( this);




        final RestaurantService restaurantService = ServiceGenerator.createService(RestaurantService.class);
        Call<Restaurant> callOneRestaurant = restaurantService.getOneRestaurant(idRestaurante);
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
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        String[] latlong = loc.toArray(new String[0]);

        double lat = Double.parseDouble(latlong[0]);
        Double lon = Double.parseDouble(latlong[1]);

        LatLng latLng = new LatLng(lat, lon);
        googleMap.setMinZoomPreference(12);

        googleMap.addMarker(new MarkerOptions()
        .position(latLng)
        .title(restaurante.getName())
        .draggable(true));


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));

    }


}
