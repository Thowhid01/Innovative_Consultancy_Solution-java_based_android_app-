package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class signup_activity extends AppCompatActivity implements View.OnClickListener {
    private Button loginSignupActivityButton;
    private EditText signUpEmailEdittext , signUpPasswordEdittext;
    private FirebaseAuth auth;
    private Button signupactivitySignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth=FirebaseAuth.getInstance();
        signUpEmailEdittext = findViewById(R.id.signUpEmailEdittextId);
        signUpPasswordEdittext=findViewById(R.id.signUpPasswordEdittextId);
        signupactivitySignupButton = findViewById(R.id.signupactivitySignupButtonid);
        signupactivitySignupButton.setOnClickListener(this);
        loginSignupActivityButton=findViewById(R.id.loginSignupActivityButtonId);
        loginSignupActivityButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.signupactivitySignupButtonid)
        {
            userSignup();
        }
        else if(view.getId()==R.id.loginSignupActivityButtonId){
            Intent intent = new Intent(signup_activity.this,login_activity.class);
            startActivity(intent);
        }
    }

    private void userSignup() {
        String email=signUpEmailEdittext.getText().toString().trim();
        String password=signUpPasswordEdittext.getText().toString().trim();
        if(email.isEmpty()){
            signUpEmailEdittext.setError("Enter A Email Address");
            signUpEmailEdittext.requestFocus();
        }
        if(Patterns.EMAIL_ADDRESS.matcher("email").matches())
        {
            signUpEmailEdittext.setError("Enter A Valid Email");
            signUpEmailEdittext.requestFocus();
        }
        if(password.isEmpty()){
            signUpPasswordEdittext.setError("Enter a Password");
            signUpPasswordEdittext.requestFocus();
        }
        if(password.length()<6){
            signUpPasswordEdittext.setError("Enter a Strong Password");
            signUpPasswordEdittext.requestFocus();
        }
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup_activity.this,"Sign Up Successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(signup_activity.this,"Sign Up NOT Successful!!! Try Again.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}