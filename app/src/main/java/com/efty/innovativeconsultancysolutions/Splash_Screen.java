package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.setTitle("Splash_Screen");
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#25593E"));
        actionBar.setBackgroundDrawable(colorDrawable);


        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                    Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                    e.fillInStackTrace();
                }
            }
        };
        thread.start();
    }
}