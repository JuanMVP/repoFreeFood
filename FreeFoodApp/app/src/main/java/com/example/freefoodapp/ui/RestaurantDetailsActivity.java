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
import com.example.freefoodapp.adapters.PhotoAdapter;
import com.example.freefoodapp.models.OneResponseContainer;
import com.example.freefoodapp.models.Restaurant;
import com.example.freefoodapp.responses.RestaurantResponse;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RestaurantService;
import com.example.freefoodapp.util.Util;
import com.example.freefoodapp.util.UtilToken;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailsActivity extends AppCompatActivity implements OnMapReadyCallback{
    private String token,restauranteId,nombre,direccion,intolerancias,horarioTelefono;
    private TextView nombreRestaurante,direccionRestaurante,horarioRestaurante,intoleranciasRestaurantes;
    private MapView mapView;
    private ViewPager fotosPage;
    private Restaurant restaurante;
    private ImageView imagenDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        final Intent i = getIntent();
        String idRestaurante = i.getStringExtra("id");

        token = UtilToken.getToken(RestaurantDetailsActivity.this);
        nombreRestaurante = findViewById(R.id.nombreRestaurante);
        direccionRestaurante = findViewById(R.id.direccionRestaurante);
        horarioRestaurante = findViewById(R.id.timetableDetailRestaurant);
        intoleranciasRestaurantes = findViewById(R.id.intoleranciasDetailsRestaurant);
        imagenDetalle = findViewById(R.id.imagenDetalle);
        /*mapView.onCreate(savedInstanceState);
        mapView.getMapAsync( this);*/




        final RestaurantService restaurantService = ServiceGenerator.createService(RestaurantService.class);
        Call<OneResponseContainer<Restaurant>> callOneRestaurant = restaurantService.getOneRestaurant(idRestaurante);
        callOneRestaurant.enqueue(new Callback<OneResponseContainer<Restaurant>>() {
            @Override
            public void onResponse(Call<OneResponseContainer<Restaurant>> call, Response<OneResponseContainer<Restaurant>> response) {
                if(response.isSuccessful()){

                    restaurante = response.body().getRows();
                    //if(restaurante.getPicture() != null){
                        Glide.with(RestaurantDetailsActivity.this).load(restaurante.getPicture()).into(imagenDetalle);
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
            public void onFailure(Call<OneResponseContainer<Restaurant>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(RestaurantDetailsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
