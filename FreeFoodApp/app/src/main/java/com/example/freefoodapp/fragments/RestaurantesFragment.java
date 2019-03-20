package com.example.freefoodapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.freefoodapp.R;
import com.example.freefoodapp.adapters.MyRestaurantesRecyclerViewAdapter;
import com.example.freefoodapp.fragments.dummy.DummyContent.DummyItem;
import com.example.freefoodapp.models.ResponseContainer;
import com.example.freefoodapp.models.Restaurant;
import com.example.freefoodapp.responses.RestaurantResponse;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RestaurantService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class RestaurantesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private MyRestaurantesRecyclerViewAdapter adapter;
    private Context ctx;
    private RecyclerView recyclerView;
    private List<Restaurant> listaRestaurantes;



    public RestaurantesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RestaurantesFragment newInstance(int columnCount) {
        RestaurantesFragment fragment = new RestaurantesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurantes_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            //instanciar Recycler View
            recyclerView = view.findViewById(R.id.restaurantesList);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //cargar RecyclerView
            listaRestaurantes = new ArrayList<>();

            final RestaurantService restaurantService = ServiceGenerator.createService(RestaurantService.class);
            Call<ResponseContainer<Restaurant>> callRestaurant = restaurantService.getRestaurants();

            callRestaurant.enqueue(new Callback<ResponseContainer<Restaurant>>() {
                @Override
                public void onResponse(Call<ResponseContainer<Restaurant>> call, Response<ResponseContainer<Restaurant>> response) {
                    if(response.isSuccessful()){
                        listaRestaurantes = response.body().getRows();
                        adapter = new MyRestaurantesRecyclerViewAdapter(ctx,
                                R.layout.fragment_restaurantes,listaRestaurantes,mListener);
                        recyclerView.setAdapter(adapter);
                    }else{
                        Toast.makeText(ctx, "Error en la peticion", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseContainer<Restaurant>> call, Throwable t) {
                    Log.e("NetworkFailure", t.getMessage());
                    Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_SHORT).show();
                }
            });

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.ctx = context;
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
