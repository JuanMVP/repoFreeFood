package com.example.freefoodapp.adapters;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.freefoodapp.R;
import com.example.freefoodapp.fragments.DeleteRecipeDialogFragment;
import com.example.freefoodapp.fragments.RecetasFragment.OnListFragmentInteractionListener;
import com.example.freefoodapp.fragments.dummy.DummyContent.DummyItem;
import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.ui.EditRecipeActivity;
import com.example.freefoodapp.ui.RecipeDetailsActivity;
import com.example.freefoodapp.viewmodel.RecipeViewModel;

import java.io.Serializable;
import java.util.List;


public class MyRecetasRecyclerViewAdapter extends RecyclerView.Adapter<MyRecetasRecyclerViewAdapter.ViewHolder> {

    private final List<Recipe> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context ctx;
    private Recipe receta;
    private RecipeViewModel recipeViewModel;

    public MyRecetasRecyclerViewAdapter(Context context, int layout,List<Recipe> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recetas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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
                DeleteRecipeDialogFragment dialogFragment = Dele

            }
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombreReceta,ingredientesReceta;
        public final ImageView imagenRecetaList, imagenFavReceta, btnEditReceta,btnDeleteReceta;
        public Recipe mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombreReceta =  view.findViewById(R.id.nombreRecetaList);
            ingredientesReceta =  view.findViewById(R.id.ingredientesRecetasList);
            imagenRecetaList = view.findViewById(R.id.recetasImagenList);
            imagenFavReceta = view.findViewById(R.id.recetasListFav);
            btnEditReceta = view.findViewById(R.id.btnGoEditReceta);
            btnDeleteReceta = view.findViewById(R.id.btnGoDeleteRecipe);
        }


} }
