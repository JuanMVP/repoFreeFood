package com.example.freefoodapp.ui;

import android.content.Intent;
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

public class EditRecipeActivity extends AppCompatActivity {

    private EditText nombreEdit,ingredientesEdit,descripcionEdit,comensalesEdit;
    private Button btnEditarReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);


        Intent i = getIntent();
        final Recipe editedRecipe = (Recipe) i.getSerializableExtra("recipe");

        nombreEdit = findViewById(R.id.nombreEditReceta);
        ingredientesEdit = findViewById(R.id.ingredientesEditReceta);
        descripcionEdit = findViewById(R.id.descripcionEditReceta);
        comensalesEdit = findViewById(R.id.comensalesEditReceta);
        btnEditarReceta = findViewById(R.id.buttonEditReceta);



        btnEditarReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Recipe recipeEdited = editedRecipe;
                editedRecipe.setName(nombreEdit.getText().toString());
                editedRecipe.setIngredients(ingredientesEdit.getText().toString());
                editedRecipe.setDescription(descripcionEdit.getText().toString());
                editedRecipe.setDinnerGuest(comensalesEdit.getText().toString());

                RecetaService recetaService = ServiceGenerator.createService(RecetaService.class, UtilToken.getToken(EditRecipeActivity.this), TipoAutenticacion.JWT);
                Call<Recipe> callEditRecipe = recetaService.editReceta(editedRecipe.getId(),editedRecipe);
                callEditRecipe.enqueue(new Callback<Recipe>() {
                    @Override
                    public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                        if(response.isSuccessful()){

                            Toast.makeText(EditRecipeActivity.this, "Receta Editada", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditRecipeActivity.this,DashboardActivity.class));

                        }else{
                            Toast.makeText(EditRecipeActivity.this, "Fallo al Editar", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Recipe> call, Throwable t) {
                        Log.e("NetworkFailure", t.getMessage());
                        Toast.makeText(EditRecipeActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



    }
}
