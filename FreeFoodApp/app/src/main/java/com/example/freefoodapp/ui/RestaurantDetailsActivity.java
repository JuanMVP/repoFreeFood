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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantDetailsActivity extends AppCompatActivity {
    private String token;
    private TextView nombreRestaurante,direccionRestaurante,horarioRestaurante,intoleranciasRestaurantes,restauranteDescripcion;
    private ViewPager fotosPage;
    private Restaurant restaurante;
    private ImageView imagenDetalleRest;

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
        horarioRestaurante = findViewById(R.id.comensalesAddReceta);
        intoleranciasRestaurantes = findViewById(R.id.intoleranciasDetailsRestaurant);
        imagenDetalleRest = findViewById(R.id.imagenDetalle);
        restauranteDescripcion = findViewById(R.id.descripcionRestaurante);





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
                    restauranteDescripcion.setText(restaurante.getDescription());

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





}
