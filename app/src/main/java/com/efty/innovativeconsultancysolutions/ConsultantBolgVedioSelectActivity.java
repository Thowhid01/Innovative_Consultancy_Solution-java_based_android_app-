package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ConsultantBolgVedioSelectActivity extends AppCompatActivity {

    Button blogConsultantBolgVedioSelectActivityBtn,videoConsultantBolgVedioSelectActivityBtn,clientConsultantBolgVedioSelectActivityBtn;
    Intent intent;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_bolg_vedio_select);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ConsultantBolgVedioSelectActivity");
        getSupportActionBar().setSubtitle("Hi..");

        intent=getIntent();
        email=intent.getStringExtra("email");
        blogConsultantBolgVedioSelectActivityBtn=findViewById(R.id.blogConsultantBolgVedioSelectActivityBtnId);
        videoConsultantBolgVedioSelectActivityBtn=findViewById(R.id.videoConsultantBolgVedioSelectActivityBtnId);
        clientConsultantBolgVedioSelectActivityBtn=findViewById(R.id.clientConsultantBolgVedioSelectActivityBtnId);
        blogConsultantBolgVedioSelectActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent1=new Intent(ConsultantBolgVedioSelectActivity.this,MakeBlogActivity.class);
                    intent1.putExtra("email",email);
                    startActivity(intent1);
            }
        });
        videoConsultantBolgVedioSelectActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ConsultantBolgVedioSelectActivity.this,UploadVedioActivity.class);
                intent1.putExtra("email",email);
                startActivity(intent1);
            }
        });
        clientConsultantBolgVedioSelectActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ConsultantBolgVedioSelectActivity.this,consultantClientDetails.class);
                intent1.putExtra("email",email);
                startActivity(intent1);
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