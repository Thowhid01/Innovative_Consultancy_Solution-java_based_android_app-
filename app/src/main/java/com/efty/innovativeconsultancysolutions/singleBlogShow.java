package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class singleBlogShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_blog_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("singleBlogShow !!");
        getSupportActionBar().setSubtitle("Hi..");

    }
}