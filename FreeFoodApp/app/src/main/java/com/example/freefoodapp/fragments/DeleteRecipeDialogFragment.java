package com.example.freefoodapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.models.ResponseContainer;
import com.example.freefoodapp.models.TipoAutenticacion;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RecetaService;
import com.example.freefoodapp.util.UtilToken;
import com.example.freefoodapp.viewmodel.RecipeViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteRecipeDialogFragment extends DialogFragment {

    public static DeleteRecipeDialogFragment newInstance() {
        return new DeleteRecipeDialogFragment();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("¿Desea eliminar el proyecto?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RecipeViewModel mViewModel = ViewModelProviders.of(getActivity()).get(RecipeViewModel.class);
                String idInmuebleBorrar = mViewModel.getSelectedIdInmuble().getValue();

                deleteRecipe(idInmuebleBorrar, getActivity());

            }
        })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }


    public void deleteRecipe(String id, final Context ctx){
        RecetaService service = ServiceGenerator.createService(RecetaService.class, UtilToken.getToken(getActivity()), TipoAutenticacion.JWT );
        Call<ResponseContainer<Recipe>> call = service.deleteOneRecipe(id);

        call.enqueue(new Callback<ResponseContainer<Recipe>>() {

            @Override
            public void onResponse(Call<ResponseContainer<Recipe>> call, Response<ResponseContainer<Recipe>> response) {
                if (response.isSuccessful()) {

                    RecipeViewModel mViewModel = ViewModelProviders.of((FragmentActivity) ctx).get(RecipeViewModel.class);
                    String idRecipe = mViewModel.getSelectedIdInmuble().getValue();
                    getRecipes(idRecipe, ctx);


                }else{
                    Toast.makeText(ctx, "Error en petición", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<ResponseContainer<Recipe>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void getRecipes(String idRecipe, final Context ctx) {
        RecetaService service = ServiceGenerator.createService(RecetaService.class);
        Call<ResponseContainer<Recipe>> call = service.getRecetas();

        call.enqueue(new Callback<ResponseContainer<Recipe>>() {

            @Override
            public void onResponse(Call<ResponseContainer<Recipe>> call, Response<ResponseContainer<Recipe>> response) {
                if (response.isSuccessful()) {
                    RecipeViewModel mViewModel = ViewModelProviders.of((FragmentActivity) ctx).get(RecipeViewModel.class);
                    mViewModel.selectRecipeList(response.body().getRows());

                } else {


                    Toast.makeText(ctx, "Error en petición", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<ResponseContainer<Recipe>> call, Throwable t) {
                Log.e("NetworkFailure", t.getMessage());
                Toast.makeText(ctx, "Error de conexión", Toast.LENGTH_SHORT).show();

            }

        });
    }

}
