package com.example.freefoodapp.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.freefoodapp.ui.RestaurantDetailsActivity;

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.nombreRestaurante.setText(holder.mItem.getName());
        holder.direccionRestaurante.setText(holder.mItem.getAddress());
        for(int i = 0; i < holder.mItem.getIntolerance().size(); i++){
            holder.intoleranciasRestaurante.setText(holder.mItem.getIntolerance().get(i).getName());
        }
        /*if(holder.mItem.getIntolerance() != null) {
            holder.intoleranciasRestaurante.setText(holder.mItem.getIntolerance().get(0).getName());
        }*/
        holder.horarioRestaurante.setText(holder.mItem.getTimetable());
        if(holder.mItem.getPicture() != null){
            Glide.with(ctx).load(holder.mItem.getPicture()).into(holder.imagenRestauranteLista);
        }else{
            Glide.with(ctx).load("https://u.tfstatic.com/restaurant_photos/665/68665/169/612/happy-day-vegetariano-vista-sala-a902a.jpg").into(holder.imagenRestauranteLista);
        }



        holder.imagenRestauranteLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, RestaurantDetailsActivity.class);
                i.putExtra("id",mValues.get(position).getId());
                ctx.startActivity(i);


            }
        });


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
            imagenRestauranteLista =  view.findViewById(R.id.recetasImagenList);
            imgLocationRestaurante =  view.findViewById(R.id.RestauranteListLocation);
            imgFavRestaurante = view.findViewById(R.id.recetasListFav);
            nombreRestaurante = view.findViewById(R.id.nombreRecetaList);
            direccionRestaurante= view.findViewById(R.id.direccionRestauranteList);
            intoleranciasRestaurante = view.findViewById(R.id.intoleranciasRestauranteList);
            horarioRestaurante = view.findViewById(R.id.horarioRestauranteList);
        }


    }
}
