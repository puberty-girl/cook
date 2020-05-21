package com.example.cook2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class M_dishes extends AppCompatActivity {
    Button burger;
    Button meat;
    Button pasta;
    Button veg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_dishes);
        burger = findViewById(R.id.button);
        meat = findViewById(R.id.button2);
        pasta = findViewById(R.id.button3);
        veg = findViewById(R.id.button4);

        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_dishes.this, Recycler.class).putExtra("id_type", 4));

            }
        });
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_dishes.this, Recycler.class).putExtra("id_type", 5));
            }
        });
        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_dishes.this, Recycler.class).putExtra("id_type", 7));

            }
        });
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(M_dishes.this, Recycler.class).putExtra("id_type", 8));

            }
        });
    }
}
