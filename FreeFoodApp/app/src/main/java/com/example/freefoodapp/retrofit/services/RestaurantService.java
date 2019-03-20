package com.example.freefoodapp.retrofit.services;

import com.example.freefoodapp.models.OneResponseContainer;
import com.example.freefoodapp.models.ResponseContainer;
import com.example.freefoodapp.models.Restaurant;
import com.example.freefoodapp.responses.RestaurantResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantService {

    @GET("/restaurants")
    Call<ResponseContainer<Restaurant>> getRestaurants();

    @GET("/restaurants/{id}")
    Call<Restaurant> getOneRestaurant(@Path("id") String id);
}
