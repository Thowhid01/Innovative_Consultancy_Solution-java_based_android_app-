package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class UploadVedioActivity extends AppCompatActivity {

    Intent intent;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_vedio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("UploadVedioActivity !!");


        intent=getIntent();
        email=intent.getStringExtra("email");
        getSupportActionBar().setSubtitle("Hi "+email);
        Toast.makeText(UploadVedioActivity.this, "Upload vedio : "+email, Toast.LENGTH_SHORT).show();

    }
}