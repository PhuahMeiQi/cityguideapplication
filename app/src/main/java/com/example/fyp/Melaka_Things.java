package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Melaka_Things extends AppCompatActivity
{
    ImageView btn_his, btn_cul,btn_acc, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melaka__things);

        btn_his= findViewById(R.id.btn_his);
        btn_cul = findViewById(R.id.btn_culture);
        btn_acc = findViewById(R.id.btn_attraction);
        btn_back = findViewById(R.id.btn_back);

        btn_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Melaka_Things.this, Melaka_History.class);
                startActivity(intent);
            }
        });

        btn_cul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Melaka_Things.this, Culture_Melaka.class);
                startActivity(intent);
            }
        });

        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Melaka_Things.this, Melaka_Popular.class);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(Melaka_Things.this, Melaka_Main.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
