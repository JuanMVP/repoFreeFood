package com.example.freefoodapp.retrofit.services;

import com.example.freefoodapp.models.ResponseContainer;
import com.example.freefoodapp.responses.RecetaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecetaService {


    @GET("/recipes")
    Call<ResponseContainer<RecetaResponse>> getRecetas();

}
