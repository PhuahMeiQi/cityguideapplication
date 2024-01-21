package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Translate_Activity extends AppCompatActivity
{
    TextView tv_chiT, tv_chiS, tv_malay, tv_indo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_);

        tv_chiT = findViewById(R.id.tv_chinese);
        tv_chiS = findViewById(R.id.tv_chineseSim);
        tv_indo = findViewById(R.id.tv_indo);
        tv_malay = findViewById(R.id.tv_malay);

        tv_chiT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Chinese Traditional",Toast.LENGTH_SHORT).show();
            }
        });

        tv_chiS.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Chinese Simplified",Toast.LENGTH_SHORT).show();
            }
        });

        tv_indo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Indonesia",Toast.LENGTH_SHORT).show();
            }
        });

        tv_malay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Bahasa Malaysia",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
