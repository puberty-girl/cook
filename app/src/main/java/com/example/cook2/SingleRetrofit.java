package com.example.cook2;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleRetrofit {

    private static SingleRetrofit instance;

    private SingleRetrofit() {}

    public static void initInstance() {
        if (instance==null){
            instance = new SingleRetrofit();
        }
    }

    public static SingleRetrofit getInstance(){
        return instance;
    }

    public RecipesApi createApiSample() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cook999.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RecipesApi messagesApi = retrofit.create(RecipesApi.class);
        return messagesApi;
    }


}
