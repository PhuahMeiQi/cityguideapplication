package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class Splash_Screen extends AppCompatActivity
{
    TextView view;
    ProgressBar bar;
    GifImageView gifImageView;
    static int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        view=findViewById(R.id.text);
        bar=findViewById(R.id.progressBar);
        gifImageView=findViewById(R.id.gifImageView);

        new CountDownTimer(2000,100){

            @Override
            public void onTick(long millisUntilFinished) {
                counter++;
                view.setText(counter+" %");
            }

            @Override
            public void onFinish() {

                view.setText("Complete");
//                bar.setVisibility(View.GONE);
                gifImageView.setImageResource(R.drawable.hero);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Splash_Screen.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                },1000);

            }
        }.start();

    }
}
