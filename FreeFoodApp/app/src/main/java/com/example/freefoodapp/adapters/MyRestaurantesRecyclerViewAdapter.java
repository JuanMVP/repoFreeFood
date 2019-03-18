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
import com.example.freefoodapp.fragments.RestaurantesFragment.OnListFragmentInteractionListener;
import com.example.freefoodapp.fragments.dummy.DummyContent.DummyItem;
import com.example.freefoodapp.models.Restaurant;
import com.example.freefoodapp.responses.RestaurantResponse;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyRestaurantesRecyclerViewAdapter extends RecyclerView.Adapter<MyRestaurantesRecyclerViewAdapter.ViewHolder> {

    private final List<Restaurant> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context ctx;

    public MyRestaurantesRecyclerViewAdapter(Context ctx, int layout, List<Restaurant> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_restaurantes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nombreRestaurante.setText(holder.mItem.getName());
        holder.direccionRestaurante.setText(holder.mItem.getAddress());
        if(holder.mItem.getIntolerance() != null) {
            holder.intoleranciasRestaurante.setText(holder.mItem.getIntolerance().getName());
        }
        holder.horarioRestaurante.setText(holder.mItem.getTimetable());
        if(holder.mItem.getListPhotos() != null){
            Glide.with(ctx).load(holder.mItem.getListPhotos().get(0)).into(holder.imagenRestauranteLista);
        }else{
            Glide.with(ctx).load("https://u.tfstatic.com/restaurant_photos/665/68665/169/612/happy-day-vegetariano-vista-sala-a902a.jpg").into(holder.imagenRestauranteLista);
        }


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public Restaurant mItem;
        public ImageView imagenRestauranteLista, imgLocationRestaurante, imgFavRestaurante;
        public TextView nombreRestaurante,direccionRestaurante,intoleranciasRestaurante,horarioRestaurante;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imagenRestauranteLista =  view.findViewById(R.id.restauranteImagenList);
            imgLocationRestaurante =  view.findViewById(R.id.RestauranteListLocation);
            imgFavRestaurante = view.findViewById(R.id.restauranteListFav);
            nombreRestaurante = view.findViewById(R.id.nombreRestauranteList);
            direccionRestaurante= view.findViewById(R.id.direccionRestauranteList);
            intoleranciasRestaurante = view.findViewById(R.id.intoleranciasRestauranteList);
            horarioRestaurante = view.findViewById(R.id.horarioRestauranteList);
        }


    }
}
