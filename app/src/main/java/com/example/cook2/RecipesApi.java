package com.example.cook2;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipesApi {

    @GET("/getrecipe")
    Call<Recipes> recipe(@Query("id") int id);

    @GET("/getrecipesbytype")
    Call <List<Recipes>> recipesbytype(@Query("id_type") int id_type);

}
