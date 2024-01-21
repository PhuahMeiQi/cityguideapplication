package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Forum_Activity extends AppCompatActivity
{
    TextView tv_create,tv_read;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_);

        tv_create = findViewById(R.id.tv_create);
        tv_read = findViewById(R.id.tv_read);

        tv_create.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  = new Intent(Forum_Activity.this, Penang_Forum_Write.class);
                startActivity(intent);
            }
        });

        tv_read.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent  = new Intent(Forum_Activity.this, Penang_Forum_Read.class);
                startActivity(intent);
            }
        });

    }
}
