package com.example.freefoodapp.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freefoodapp.R;
import com.example.freefoodapp.fragments.DeleteRecipeDialogFragment;
import com.example.freefoodapp.fragments.RecetasFavoritasFragment;
import com.example.freefoodapp.fragments.RecetasFragment.OnListFragmentInteractionListener;
import com.example.freefoodapp.fragments.dummy.DummyContent.DummyItem;
import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.models.TipoAutenticacion;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RecetaService;
import com.example.freefoodapp.ui.EditRecipeActivity;
import com.example.freefoodapp.ui.LoginActivity;
import com.example.freefoodapp.ui.RecipeDetailsActivity;
import com.example.freefoodapp.util.UtilToken;
import com.example.freefoodapp.viewmodel.RecipeViewModel;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyRecetasRecyclerViewAdapter extends RecyclerView.Adapter<MyRecetasRecyclerViewAdapter.ViewHolder> {

    private final List<Recipe> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context ctx;
    private Recipe receta;
    private int favCode = 7;
    private RecipeViewModel recipeViewModel;
    private boolean isFav,fragmentFav;

    public MyRecetasRecyclerViewAdapter(Context context, int layout,List<Recipe> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = context;
    }

    public MyRecetasRecyclerViewAdapter(Context ctx, List<Recipe> items, OnListFragmentInteractionListener mListener, boolean fragmentFav) {
        this.ctx = ctx;
        mValues = items;
        this.mListener = mListener;
        this.fragmentFav = fragmentFav;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recetas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(mValues == null){

            holder.mItem = mValues.get(position);
            holder.nombreReceta.setText(holder.mItem.getName());
            holder.ingredientesReceta.setText(holder.mItem.getIngredients());
            Glide.with(ctx).load(holder.mItem.getPicture()).into(holder.imagenRecetaList);
            holder.imagenRecetaList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx, RecipeDetailsActivity.class);
                    i.putExtra("id",mValues.get(position).getId());
                    ctx.startActivity(i);
                }
            });

        }else{

            holder.mItem = mValues.get(position);
            holder.nombreReceta.setText(holder.mItem.getName());
            holder.ingredientesReceta.setText(holder.mItem.getIngredients());
            Glide.with(ctx).load(holder.mItem.getPicture()).into(holder.imagenRecetaList);
            holder.imagenRecetaList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx, RecipeDetailsActivity.class);
                    i.putExtra("id",mValues.get(position).getId());
                    ctx.startActivity(i);
                }
            });



        }



        holder.btnEditReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, EditRecipeActivity.class);
                i.putExtra("recipe", (Serializable) mValues.get(position));
                ctx.startActivity(i);
            }
        });

        holder.btnDeleteReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeViewModel = ViewModelProviders.of((FragmentActivity) ctx).get(RecipeViewModel.class);
                recipeViewModel.selectIdRecipe(holder.mItem.getId());
                DeleteRecipeDialogFragment dialogFragment = DeleteRecipeDialogFragment.newInstance();
                dialogFragment.show(((FragmentActivity) ctx).getSupportFragmentManager(), "dialog");


            }
        });

        /*holder.imagenFavReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (favCode == 7) {
                    RecetaService service = ServiceGenerator.createService(RecetaService.class, UtilToken.getToken(ctx), TipoAutenticacion.JWT);

                    Call<Recipe> call = service.addRecipeFav(holder.mItem.getId());
                    call.enqueue(new Callback<Recipe>() {
                        @Override
                        public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(ctx, "Erro en la peticion", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ctx, "Añadido a favoritos", Toast.LENGTH_LONG).show();
                                holder.mItem.setFav(true);
                                holder.imagenFavReceta.setImageResource(R.drawable.ic_heart);
                                //     refreshList(holder);
                            }
                        }

                        @Override
                        public void onFailure(Call<Recipe> call, Throwable t) {
                            Toast.makeText(ctx, "Error de conexion", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    RecetaService service = ServiceGenerator.createService(RecetaService.class, UtilToken.getToken(ctx), TipoAutenticacion.JWT);

                    Call<Recipe> call = service.deleteRecipeFav(holder.mItem.getId());
                    call.enqueue(new Callback<Recipe>() {
                        @Override
                        public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                            if (!response.isSuccessful()) {
//                            Toast.makeText(contexto, "Error in request", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ctx, "Deleted from favourites", Toast.LENGTH_LONG).show();
                                holder.mItem.setFav(false);
                                holder.imagenFavReceta.setImageResource(R.drawable.ic_corazon);
                                //    refreshList(holder);
                            }
                        }

                        @Override
                        public void onFailure(Call<Recipe> call, Throwable t) {
                            Toast.makeText(ctx, "Error de conexion", Toast.LENGTH_SHORT).show();

                        }
                    });
                }











                }

        });*/


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombreReceta,ingredientesReceta;
        public final ImageView imagenRecetaList, btnEditReceta,btnDeleteReceta;
        public Recipe mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombreReceta =  view.findViewById(R.id.nombreRecetaList);
            ingredientesReceta =  view.findViewById(R.id.ingredientesRecetasList);
            imagenRecetaList = view.findViewById(R.id.recetasImagenList);
            //imagenFavReceta = view.findViewById(R.id.recetasListFav);
            btnEditReceta = view.findViewById(R.id.btnGoEditReceta);
            btnDeleteReceta = view.findViewById(R.id.btnGoDeleteRecipe);
        }


} }
