package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class login_activity extends AppCompatActivity implements View.OnClickListener {
    private Button signupLoginactivityButton;
    private Button loginActivityLoginBtn;
    private EditText loginActivityEmailEd,loginActivityPasswordEd;
    private Button loginAsaConsultantActivityLoginBtn;
    private FirebaseAuth auth;
    String consultant;
    ArrayList<User> users;
    ArrayList<Consultant> consultants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("login_activity");
        getSupportActionBar().setSubtitle("Hi...");



        auth=FirebaseAuth.getInstance();
        loginActivityLoginBtn = findViewById(R.id.loginActivityLoginBtnId);
        loginActivityLoginBtn.setOnClickListener(this);
        loginActivityEmailEd=findViewById(R.id.loginActivityEmailEdId);
        loginActivityPasswordEd=findViewById(R.id.loginActivityPasswordEdId);
        signupLoginactivityButton=findViewById(R.id.signupLoginactivityButtonid);
        loginAsaConsultantActivityLoginBtn=findViewById(R.id.loginAsaConsultantActivityLoginBtnId);
        loginAsaConsultantActivityLoginBtn.setOnClickListener(this);
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
            consultant="User";
            userLogin();
        }
        else if(view.getId()==R.id.loginAsaConsultantActivityLoginBtnId){
            consultant="Consultant";
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
                    try {

                        if (consultant.equals("Consultant")){
                            Toast.makeText(login_activity.this,"Login successful as a Consultant",Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent(login_activity.this,ConsultantBolgVedioSelectActivity.class);
                            intent1.putExtra("email",loginActivityEmailEd.getText().toString());
                            startActivity(intent1);

                        }
                        else if (consultant.equals("User")){
                            Toast.makeText(login_activity.this,"Login successful as a User",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(login_activity.this,UserHomeActivity.class);
                            intent.putExtra("email",loginActivityEmailEd.getText().toString());
                            startActivity(intent);
                       }

                    }catch (Exception e){
                        Toast.makeText(login_activity.this, "Problem : "+e, Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Login not successfull",Toast.LENGTH_SHORT).show();
                }
            }
        });

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