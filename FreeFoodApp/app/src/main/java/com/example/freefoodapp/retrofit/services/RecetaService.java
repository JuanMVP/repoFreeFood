package com.example.freefoodapp.retrofit.services;

import com.example.freefoodapp.models.OneResponseContainer;
import com.example.freefoodapp.models.Recipe;
import com.example.freefoodapp.models.ResponseContainer;
import com.example.freefoodapp.responses.RecetaResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RecetaService {


    @GET("/recipes")
    Call<ResponseContainer<Recipe>> getRecetas();

    @GET("/recipes/{id}")
    Call<Recipe> getOneRecipe(@Path("id") String id);

    @Multipart
    @POST("/recipes")
    Call<Recipe> addReceta(@Part MultipartBody.Part picture,
                           @Part("name")RequestBody name,
                           @Part("ingredients")RequestBody ingredients,
                           @Part("description")RequestBody description,
                           @Part("dinnerGuest")RequestBody dinnerGuest);

    @PUT("/recipes/{id}")
    Call<Recipe> editReceta (@Path("id")String id, @Body Recipe editedRecipe);

    @DELETE("recipes/{id}")
    Call<ResponseContainer<Recipe>> deleteOneRecipe(@Path("id") String id);

    @GET("/recipes/fav")
    Call<ResponseContainer<Recipe>> getFavouritesRecipes();

    @POST("/recipes/fav/{id}")
    Call<Recipe> addRecipeFav(@Path("id") String id);

    @DELETE("/recipes/fav/{id}")
    Call<Recipe> deleteRecipeFav(@Path("id") String id);



}
