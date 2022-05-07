package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class getting_started extends AppCompatActivity {
    private Button loginActivityGettingButton;
    private Button signupgettingStartActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);

        loginActivityGettingButton=findViewById(R.id.loginActivityGettingButtonId);
        signupgettingStartActivityButton=findViewById(R.id.signupgettingStartActivityButtonid);
       signupgettingStartActivityButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(getting_started.this,signup_activity.class);
               startActivity(intent);
           }
       });
        loginActivityGettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getting_started.this,login_activity.class);
                startActivity(intent);
            }
        });
    }
}