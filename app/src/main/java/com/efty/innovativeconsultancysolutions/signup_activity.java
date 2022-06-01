package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
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
    private Button catagoryConsultantbutton,catagoryUserbutton;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth=FirebaseAuth.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("signup_activity !!");
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#25593E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        signUpEmailEdittext = findViewById(R.id.signUpEmailEdittextId);
        signUpPasswordEdittext=findViewById(R.id.signUpPasswordEdittextId);
        loginSignupActivityButton=findViewById(R.id.loginSignupActivityButtonId);
        loginSignupActivityButton.setOnClickListener(this);
        catagoryConsultantbutton =findViewById(R.id.signupasConsultantbuttonId);
        catagoryUserbutton=findViewById(R.id.signupasUserbuttonId);
        catagoryConsultantbutton.setOnClickListener(this);
        catagoryUserbutton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
       try{
           if(view.getId()==R.id.signupasConsultantbuttonId)
           {
               userSignup();
        /*   Intent intent=new Intent(signup_activity.this,information_setup.class);
            intent.putExtra("select","consultant");
            startActivity(intent);*/
               num=1;
           }
           if (view.getId()==R.id.signupasUserbuttonId){
               userSignup();
        /*   Intent intent=new Intent(signup_activity.this,information_setup.class);
            intent.putExtra("select","user");
            startActivity(intent);*/
               num=2;
           }
           else if(view.getId()==R.id.loginSignupActivityButtonId){
               Intent intent = new Intent(signup_activity.this,login_activity.class);
               startActivity(intent);
           }
       }
       catch (Exception e){
         //  Toast.makeText(signup_activity.this, "Enter the values", Toast.LENGTH_SHORT).show();
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
                    if(num==1){
                        Intent intent=new Intent(signup_activity.this,information_setup.class);
                        intent.putExtra("select",num);
                        intent.putExtra("email",signUpEmailEdittext.getText().toString().trim());
                        signUpEmailEdittext.setText("");
                        signUpPasswordEdittext.setText("");
                        startActivity(intent);

                    }
                    else if(num==2){
                        Intent intent=new Intent(signup_activity.this,information_setup.class);
                        intent.putExtra("select",num);
                        intent.putExtra("email",signUpEmailEdittext.getText().toString().trim());
                        signUpEmailEdittext.setText("");
                        signUpPasswordEdittext.setText("");
                        startActivity(intent);

                    }
                }
                else {
                    Toast.makeText(signup_activity.this,"Sign Up NOT Successful!!! Try Again."+task.getException(),Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //on back press button
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

}