package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button gettingStartedButton;
    private Button loginMainActivityButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gettingStartedButton=findViewById(R.id.gettingStartedButtonId);
        loginMainActivityButton=findViewById(R.id.loginMainActivityButtonId);
        loginMainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login_activity.class);
                startActivity(intent);
            }
        });
        gettingStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,getting_started.class);
                startActivity(intent);
            }

        });

    }
}
