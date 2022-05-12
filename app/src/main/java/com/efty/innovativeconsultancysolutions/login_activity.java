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


public class login_activity extends AppCompatActivity implements View.OnClickListener {
    private Button signupLoginactivityButton;
    private Button loginActivityLoginBtn;
    private EditText loginActivityEmailEd,loginActivityPasswordEd;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth=FirebaseAuth.getInstance();
        loginActivityLoginBtn = findViewById(R.id.loginActivityLoginBtnId);
        loginActivityLoginBtn.setOnClickListener(this);
        loginActivityEmailEd=findViewById(R.id.loginActivityEmailEdId);
        loginActivityPasswordEd=findViewById(R.id.loginActivityPasswordEdId);
        signupLoginactivityButton=findViewById(R.id.signupLoginactivityButtonid);

        signupLoginactivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_activity.this,signup_activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.loginActivityLoginBtnId){
            userLogin();
        }
    }

    private void userLogin() {
        String email=loginActivityEmailEd.getText().toString().trim();
        String password=loginActivityPasswordEd.getText().toString().trim();
        if(email.isEmpty()){
            loginActivityEmailEd.setError("Enter A Email Address");
            loginActivityEmailEd.requestFocus();
            return;
        }
        if(Patterns.EMAIL_ADDRESS.matcher("email").matches())
        {
            loginActivityEmailEd.setError("Enter A correct Email");
            loginActivityEmailEd.requestFocus();
            return;
        }
        if(password.isEmpty()){
            loginActivityPasswordEd.setError("Enter a Password");
            loginActivityPasswordEd.requestFocus();
            return;
        }
        if(password.length()<6){
            loginActivityPasswordEd.setError("Enter a correct Password");
            loginActivityPasswordEd.requestFocus();
            return;
        }
       auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login successfull",Toast.LENGTH_SHORT).show();
                   // startActivity(new Intent(login_activity.this,information_setup.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login not successfull",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}