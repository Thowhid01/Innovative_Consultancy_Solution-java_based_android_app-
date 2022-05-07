package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class information_setup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_setup);
        Intent intent=getIntent();
        String catagory=intent.getStringExtra("select");
        Toast.makeText(information_setup.this, "catagory : "+catagory, Toast.LENGTH_SHORT).show();
    }
}