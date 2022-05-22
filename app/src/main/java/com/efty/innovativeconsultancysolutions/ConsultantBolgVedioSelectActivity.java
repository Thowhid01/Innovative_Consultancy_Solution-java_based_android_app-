package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConsultantBolgVedioSelectActivity extends AppCompatActivity {

    Button blogConsultantBolgVedioSelectActivityBtn,videoConsultantBolgVedioSelectActivityBtn;
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



    }
}