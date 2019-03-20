package com.example.freefoodapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freefoodapp.R;
import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.models.TipoAutenticacion;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RecetaService;
import com.example.freefoodapp.util.Util;
import com.example.freefoodapp.util.UtilToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecipeActivity extends AppCompatActivity {

    private String token;
    private EditText addName, addIngredients, addDescription, addDinnerGuest;
    private Button btnCrear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        token = UtilToken.getToken(AddRecipeActivity.this);
        addName = findViewById(R.id.nombreAddReceta);
        addIngredients = findViewById(R.id.ingredientesAddReceta);
        addDescription = findViewById(R.id.descripcionAddReceta);
        addDinnerGuest = findViewById(R.id.comensalesAddReceta);
        btnCrear = findViewById(R.id.buttonAddReceta);


        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Recipe addRecipe = new Recipe(
                       addName.getText().toString(),
                       addIngredients.getText().toString(),
                       addDescription.getText().toString(),
                       addDinnerGuest.getText().toString()
               );


                RecetaService service = ServiceGenerator.createService(RecetaService.class, token, TipoAutenticacion.JWT);
                Call<Recipe> call = service.addReceta(addRecipe);

                call.enqueue(new Callback<Recipe>() {
                    @Override
                    public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(AddRecipeActivity.this, "Receta creada!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(AddRecipeActivity.this, "Fallo al crear inmueble", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Recipe> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(AddRecipeActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }
}
