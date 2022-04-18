package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup_activity extends AppCompatActivity {
    private Button loginSignupActivityButton;
    private EditText signUpEmailEdittext , signUpPasswordEdittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpEmailEdittext = findViewById(R.id.signUpEmailEdittextId);
        signUpPasswordEdittext=findViewById(R.id.signUpPasswordEdittextId);
        loginSignupActivityButton = findViewById(R.id.loginSignupActivityButtonId);
        loginSignupActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup_activity.this,login_activity.class);
                startActivity(intent);
            }
        });
    }
}