package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Melaka_Main extends AppCompatActivity {

    ImageView btn_popular, btn_local, btn_acc,btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melaka__main);
        btn_popular = findViewById(R.id.btn_his);
        btn_local = findViewById(R.id.btn_culture);
        btn_acc = findViewById(R.id.btn_attraction);
        btn_back = findViewById(R.id.btn_back);


        btn_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Melaka_Main.this, Melaka_Things.class);
                startActivity(intent);
            }
        });

        btn_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Melaka_Main.this, Melaka_Cuisine.class);
                startActivity(intent);
            }
        });

        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Melaka_Main.this, Melaka_Hotel.class);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Melaka_Main.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
