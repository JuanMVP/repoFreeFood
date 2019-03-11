package com.example.freefoodapp.retrofit.services;

import com.example.freefoodapp.responses.AuthAndRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthAndRegisterService {

    @POST("/auth")
    Call<AuthAndRegisterResponse> login(@Header("Authorization") String authorization);
}
