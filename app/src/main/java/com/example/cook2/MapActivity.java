package com.example.cook2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.cook2.Recycler;

public class MapActivity extends AppCompatActivity {

    ImageButton what_cook;
    ImageButton salads;
    ImageButton soups;
    ImageButton starters;
    ImageButton main_dishes;
    ImageButton deserts;
    ImageButton beverages;
    ImageButton bakery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        what_cook = findViewById(R.id.what_cook);
        salads = findViewById(R.id.salads);
        soups = findViewById(R.id.soups);
        starters = findViewById(R.id.starters);
        main_dishes = findViewById(R.id.main_dishes);
        deserts = findViewById(R.id.deserts);
        beverages = findViewById(R.id.beverages);
        bakery = findViewById(R.id.bakery);

        what_cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, What_to_cook.class));

            }
        });
        salads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, Recycler.class).putExtra("id_type", 1));

            }
        });
        soups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, Recycler.class).putExtra("id_type", 2));
            }
        });
        starters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, Recycler.class).putExtra("id_type", 3));

            }
        });
        main_dishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, M_dishes.class));

            }
        });
        deserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, Recycler.class).putExtra("id_type", 9));

            }
        });
        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, Recycler.class).putExtra("id_type", 10));

            }
        });
        bakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, Recycler.class).putExtra("id_type", 11));

            }
        });


    }

        public static String TAG = "dataLogs";
        //TextView myText;
       /* @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            myText = findViewById(R.id.txt);
            downloadData(SingleRetrofit.getInstance().createApiSample(), 1);

        }*/
       public void downloadData (RecipesApi recipesApi, int id){

            Call<Recipes> recipes = recipesApi.recipe(id);

            recipes.enqueue(new Callback<Recipes>() {
                @Override
                public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                    if (response.isSuccessful()) {
                        Recipes recipe = response.body();
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