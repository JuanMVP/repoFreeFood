package com.example.freefoodapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.freefoodapp.R;
import com.example.freefoodapp.adapters.MyRecetasRecyclerViewAdapter;
import com.example.freefoodapp.fragments.dummy.DummyContent;
import com.example.freefoodapp.fragments.dummy.DummyContent.DummyItem;
import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.models.ResponseContainer;
import com.example.freefoodapp.models.TipoAutenticacion;
import com.example.freefoodapp.retrofit.generator.ServiceGenerator;
import com.example.freefoodapp.retrofit.services.RecetaService;
import com.example.freefoodapp.util.UtilToken;

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
public class RecetasFavoritasFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private RecetasFragment.OnListFragmentInteractionListener mListener;
    private Context ctx;
    private List<Recipe> listaRecetas;
    private MyRecetasRecyclerViewAdapter adapter;
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjOGI2NmZlMDc5ZmIzMjBlYTExYTQ5NiIsImlhdCI6MTU1MzIyNzgyMX0.N1uvotyd2yW3j-M1-aNoVHOPrI83-X9DTTobdLc1Vt4";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecetasFavoritasFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecetasFavoritasFragment newInstance(int columnCount) {
        RecetasFavoritasFragment fragment = new RecetasFavoritasFragment();
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
        View view = inflater.inflate(R.layout.fragment_recetasfavoritas_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            listaRecetas = new ArrayList<>();

            RecetaService service = ServiceGenerator.createService(RecetaService.class, token , TipoAutenticacion.JWT);
            Call<ResponseContainer<Recipe>> callFavProperty = service.getFavouritesRecipes();

            callFavProperty.enqueue(new Callback<ResponseContainer<Recipe>>() {
                @Override
                public void onResponse(Call<ResponseContainer<Recipe>> call, Response<ResponseContainer<Recipe>> response) {
                    if(response.isSuccessful()){
                        listaRecetas = response.body().getRows();
                        adapter = new MyRecetasRecyclerViewAdapter(ctx,listaRecetas,mListener,true);
                        recyclerView.setAdapter(adapter);
                    }else{
                        Toast.makeText(ctx, "Error en la peticion", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseContainer<Recipe>> call, Throwable t) {
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
        ctx = context;
        if (context instanceof RecetasFragment.OnListFragmentInteractionListener) {
            mListener = (RecetasFragment.OnListFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
