package com.example.cook2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cook2.Models.Recycler;

public class MapActivity extends AppCompatActivity {

    Button what_cook;
    Button salads;
    Button soups;
    Button starters;
    Button main_dishes;
    Button deserts;
    Button beverages;
    Button bakery;

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
                startActivity (new Intent ( MapActivity.this, What_to_cook.class));

            }
        });
        salads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent ( MapActivity.this, Recycler.class));

            }
        });
        soups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent ( MapActivity.this, Recycler.class));

            }
        });
        starters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent ( MapActivity.this, Recycler.class));

            }
        });
        main_dishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent ( MapActivity.this, Recycler.class));

            }
        });
        deserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent ( MapActivity.this, Recycler.class));

            }
        });
        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent ( MapActivity.this, Recycler.class));

            }
        });
        bakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent ( MapActivity.this, Recycler.class));

            }
        });
    }
}
