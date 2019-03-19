package com.example.freefoodapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.freefoodapp.R;
import com.example.freefoodapp.fragments.RecetasFragment.OnListFragmentInteractionListener;
import com.example.freefoodapp.fragments.dummy.DummyContent.DummyItem;
import com.example.freefoodapp.models.Recipe;

import java.util.List;


public class MyRecetasRecyclerViewAdapter extends RecyclerView.Adapter<MyRecetasRecyclerViewAdapter.ViewHolder> {

    private final List<Recipe> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context ctx;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombreReceta.setText(holder.mItem.getName());
        holder.ingredientesReceta.setText(holder.mItem.getIngredients());
        Glide.with(ctx).load(holder.mItem.getPicture()).into(holder.imagenRecetaList);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nombreReceta,ingredientesReceta;
        public final ImageView imagenRecetaList;
        public Recipe mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nombreReceta =  view.findViewById(R.id.nombreRecetaList);
            ingredientesReceta =  view.findViewById(R.id.ingredientesRecetasList);
            imagenRecetaList = view.findViewById(R.id.recetasImagenList);
        }


} }
