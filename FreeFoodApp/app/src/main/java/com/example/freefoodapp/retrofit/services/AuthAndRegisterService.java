package com.example.freefoodapp.retrofit.services;

import com.example.freefoodapp.models.Register;
import com.example.freefoodapp.responses.AuthAndRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthAndRegisterService {

    @POST("/auth")
    Call<AuthAndRegisterResponse> login(@Header("Authorization") String authorization);

    @POST("/users")
    Call<AuthAndRegisterResponse> register(@Body Register registro);
}
