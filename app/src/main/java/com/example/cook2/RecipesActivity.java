package com.example.cook2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesActivity extends AppCompatActivity {

    //TODO Recipes
    private  static String TAG = "RecipesActivityCheck";
    Recipes recipe;
    private  int id;
    TextView myText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        myText = findViewById(R.id.myTxt);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }
        downloadData(SingleRetrofit.getInstance().createApiSample(), id);
    }


    public void downloadData (RecipesApi recipesApi, int id){

        Call<Recipes> recipes = recipesApi.recipe(id);

        recipes.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                if (response.isSuccessful()) {
                    recipe = response.body();
                    myText.setText(recipe.getName());
                    //Toast.makeText(getApplicationContext(), recipe.getName(), Toast.LENGTH_LONG);
                    //myText.setText(recipe.getName());

                    Log.d(TAG, "response Id:" + recipe.getId() + " Name:" + recipe.getName() + " Id_type:" + recipe.getId_type());
                } else {
                    Log.d(TAG, "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                Log.d(TAG, "failure " + t);
            }
        });
    }
}
