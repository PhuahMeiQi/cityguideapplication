package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{
    ImageButton btn_Penang, btn_Melaka,btn_Map,btn_weather,btn_compass,btn_forum;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Penang = findViewById(R.id.btn_penang);
        btn_Melaka = findViewById(R.id.btn_melaka);
        btn_compass = findViewById(R.id.btn_compass);
        btn_Map = findViewById(R.id.btn_map);
        btn_weather = findViewById(R.id.btn_weather);
        btn_forum = findViewById(R.id.btn_comment);


        btn_Penang.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent  = new Intent(MainActivity.this, Penang_Main.class);
                startActivity(intent);
                finish();
            }
        });

        btn_Melaka.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent  = new Intent(MainActivity.this, Melaka_Main.class);
                startActivity(intent);
                finish();
            }
        });

        btn_weather.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent  = new Intent(MainActivity.this, Weather_Activity.class);
                startActivity(intent);
            }
        });

        btn_compass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent  = new Intent(MainActivity.this, Compass_Activity.class);
                startActivity(intent);
            }
        });

        btn_Map.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent  = new Intent(MainActivity.this, Map_Activity.class);
                startActivity(intent);
            }
        });

        btn_forum.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent  = new Intent(MainActivity.this, Forum_Activity.class);
                startActivity(intent);
            }
        });

    }
}
