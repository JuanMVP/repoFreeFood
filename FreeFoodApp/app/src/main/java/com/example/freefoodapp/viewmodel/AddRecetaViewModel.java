package com.example.freefoodapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.models.TipoAutenticacion;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RecetaService;
import com.example.freefoodapp.util.Util;
import com.example.freefoodapp.util.UtilToken;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRecetaViewModel extends AndroidViewModel {
    public AddRecetaViewModel(@NonNull Application application){
        super(application);
    }

    public void AddReceta(Recipe receta, final DialogInterface dialog){
        RecetaService recetaService = ServiceGenerator.createService(RecetaService.class, Util.getToken(getApplication().getApplicationContext()), TipoAutenticacion.JWT);
        Call<Recipe> recipeCall = recetaService.addReceta(receta);

        recipeCall.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if(response.isSuccessful()){
                    dialog.dismiss();
                }else{
                    Toast.makeText(getApplication().getApplicationContext(), "Error al añadir", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Log.e("NetworkError",t.getMessage());
                Toast.makeText(getApplication().getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();


            }
        });
    }

}
