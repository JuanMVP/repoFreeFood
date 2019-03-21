package com.example.freefoodapp.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freefoodapp.R;
import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RecetaService;
import com.example.freefoodapp.util.UtilToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailsActivity extends AppCompatActivity {
    private String token;
    private TextView nombreReceta, ingredientesReceta,comensalesReceta,descripcionReceta;
    private ImageView imagenDetellaReceta;
    private Recipe receta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final Intent i = getIntent();
        String idReceta = i.getStringExtra("id");

        token = UtilToken.getToken(RecipeDetailsActivity.this);
        nombreReceta = findViewById(R.id.nombreReceta);
        ingredientesReceta = findViewById(R.id.ingredientesRecetaDetalle);
        comensalesReceta = findViewById(R.id.comensalesEditReceta);
        descripcionReceta = findViewById(R.id.descripcionReceta);
        imagenDetellaReceta = findViewById(R.id.imagenRecetaDetalle);

        final RecetaService recetaService = ServiceGenerator.createService(RecetaService.class);
        Call<Recipe> callOneRecipe = recetaService.getOneRecipe(idReceta);
        callOneRecipe.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if(response.isSuccessful()){
                    receta = response.body();
                    Glide.with(RecipeDetailsActivity.this).load(receta.getPicture()).into(imagenDetellaReceta);
                    nombreReceta.setText(receta.getName());
                    ingredientesReceta.setText(receta.getIngredients());
                    comensalesReceta.setText(receta.getDinnerGuest());
                    descripcionReceta.setText(receta.getDescription());
                }else{
                    Toast.makeText(RecipeDetailsActivity.this, "Error al ver la Receta", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(RecipeDetailsActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
