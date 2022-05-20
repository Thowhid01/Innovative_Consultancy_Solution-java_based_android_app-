package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class getting_started extends AppCompatActivity {
    private Button loginActivityGettingButton;
    private Button signupgettingStartActivityButton;
    private Button facebookSignUp;
    private Button loginAsaConsultantgettingStartActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        this.setTitle("Getting Started!!");
        loginActivityGettingButton=findViewById(R.id.loginActivityGettingButtonId);
        signupgettingStartActivityButton=findViewById(R.id.signupgettingStartActivityButtonid);
        loginAsaConsultantgettingStartActivityButton=findViewById(R.id.loginAsaConsultantgettingStartActivityButtonid);
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

        facebookSignUp=findViewById(R.id.facebookSignupgettingStartActivityButtonid);
        facebookSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getting_started.this,UserHomeActivity.class));
            }
        });
        loginAsaConsultantgettingStartActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getting_started.this,login_activity.class);
                intent.putExtra("consultant","Consultant");
                startActivity(intent);
            }
        });
    }
}