package com.example.freefoodapp.retrofit.services;

import com.example.freefoodapp.models.OneResponseContainer;
import com.example.freefoodapp.models.ResponseContainer;
import com.example.freefoodapp.responses.RestaurantResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantService {

    @GET("/restaurants")
    Call<ResponseContainer<RestaurantResponse>> getRestaurants();

    @GET("/restaurants/{id}")
    Call<OneResponseContainer<RestaurantResponse>> getOneRestaurant();
}
