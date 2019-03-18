package com.example.freefoodapp.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freefoodapp.R;
import com.example.freefoodapp.models.OneResponseContainer;
import com.example.freefoodapp.models.Restaurant;
import com.example.freefoodapp.responses.RestaurantResponse;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RestaurantService;
import com.example.freefoodapp.util.Util;
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
    private String token;
    private TextView nombreRestaurante,direccionRestaurante,horarioRestaurante,intoleranciasRestaurantes;
    private MapView mapView;
    private SliderLayout sliderLayout;
    private RestaurantResponse restaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final Intent i = getIntent();
        String idRestaurante = i.getStringExtra("id");

        token = Util.getToken(this);
        nombreRestaurante = findViewById(R.id.nombreRestaurante);
        direccionRestaurante = findViewById(R.id.direccionRestaurante);
        horarioRestaurante = findViewById(R.id.timetableDetailRestaurant);
        intoleranciasRestaurantes = findViewById(R.id.intoleranciasDetailsRestaurant);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync( this);

        final DefaultSliderView sliderView = new DefaultSliderView(RestaurantDetailsActivity.this);

        RestaurantService restaurantService = ServiceGenerator.createService(RestaurantService.class);
        Call<OneResponseContainer<RestaurantResponse>> callOneRestaurant = restaurantService.getOneRestaurant(idRestaurante);
        callOneRestaurant.enqueue(new Callback<OneResponseContainer<RestaurantResponse>>() {
            @Override
            public void onResponse(Call<OneResponseContainer<RestaurantResponse>> call, Response<OneResponseContainer<RestaurantResponse>> response) {
                if(response.isSuccessful()){

                    restaurante = response.body().getRows();
                    if(response.body().getRows().getListaFotos().size() == 0){
                        for (int i = 0; i<4; i++) {
                            sliderView.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoeXvLyc0OuLboMyi0lgP_nA-LsvTDCI4_F-OeICotZOloNNFO");
                            sliderLayout.addSliderView(sliderView);

                        }
                    }else{
                        List<String> listaFotos = new ArrayList<>(response.body().getRows().getListaFotos());
                        for (int i = 0; i < listaFotos.size(); i++){

                            String urlFotos = response.body().getRows().getListaFotos().get(i);
                            sliderView.setImageUrl(urlFotos);
                            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                            sliderLayout.addSliderView(sliderView);
                        }
                    }


                    nombreRestaurante.setText(restaurante.getName());
                    direccionRestaurante.setText(restaurante.getAddress());
                    intoleranciasRestaurantes.setText(restaurante.getIntoleranceId().getName());
                    horarioRestaurante.setText(restaurante.getTimeTable());

                }else{

                    Toast.makeText(RestaurantDetailsActivity.this, "Error al ver el Restaurante", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<OneResponseContainer<RestaurantResponse>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(RestaurantDetailsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
