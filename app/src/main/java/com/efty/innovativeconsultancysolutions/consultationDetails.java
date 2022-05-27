package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class consultationDetails extends AppCompatActivity {

    TextView nameTv,emailTv,phoneTv,typeTv,dateTv;
    EditText consultantDetailsEd,userNameEd,userEmailEd;
    Button submitBtn,homeBtn;
    HashMap<String,Object> hashMap=new HashMap<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("consultationDetails !!");
        submitBtn=findViewById(R.id.submitBtnId);
        homeBtn=findViewById(R.id.homeBtnId);
        nameTv=findViewById(R.id.consultantNameTv);
        emailTv=findViewById(R.id.consultantemailTv);
        phoneTv=findViewById(R.id.consultantphoneTv);
        typeTv=findViewById(R.id.consultanttypeTv);
        dateTv=findViewById(R.id.consultantdateTv);
        userEmailEd=findViewById(R.id.useremailEdEtId);
        consultantDetailsEd=findViewById(R.id.consultationDetailsId);
        userNameEd=findViewById(R.id.userNameEdEtId);


        Intent intent=getIntent();
        nameTv.setText("Name : "+intent.getStringExtra("cname").toString().trim());
        emailTv.setText("Email : "+intent.getStringExtra("cemail").toString().trim());
        typeTv.setText("Type : "+intent.getStringExtra("ctype").toString().trim());
        phoneTv.setText("Phone : "+intent.getStringExtra("cphone").toString().trim());
        LocalDate date= LocalDate.now();
        dateTv.setText("Date : "+date);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=userNameEd.getText().toString();
                String userEmail=userEmailEd.getText().toString().trim();
                hashMap.put("consultantName",intent.getStringExtra("cname").toString());
                hashMap.put("consultantEmail",intent.getStringExtra("cemail").toString());
                hashMap.put("consultantPhone",intent.getStringExtra("cphone").toString());
                hashMap.put("consultantType",intent.getStringExtra("ctype").toString());
                hashMap.put("consultatDetails",consultantDetailsEd.getText().toString());
                hashMap.put("consultationDate",date.toString());
                hashMap.put("userEmail",userEmail);
                hashMap.put("userName",userName);
                FirebaseDatabase.getInstance().getReference().child("ConsultationDetails").push().setValue(hashMap);

                Toast.makeText(consultationDetails.this, "data added successfully", Toast.LENGTH_SHORT).show();
                Intent email = new Intent(Intent.ACTION_SEND);
                String to=intent.getStringExtra("cemail").toString();
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, "Write about your query ?");
                email.putExtra(Intent.EXTRA_TEXT, "Describe about your query !!");

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(consultationDetails.this,MainActivity.class));
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

}