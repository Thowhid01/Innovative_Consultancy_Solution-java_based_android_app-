
package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OneConsultant extends AppCompatActivity {

    private TextView nameTv,expertTv,phoneTv,aboutNameTv,aboutTv,genderTv,bloodTv,dateTv,emailTv;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_consultant);
        this.setTitle("OneConsultant");
        nameTv=findViewById(R.id.nameOneConsultantTvId);
        phoneTv=findViewById(R.id.phoneOneConsultantTvId);
        expertTv=findViewById(R.id.expertOneConsultantTvId);
        emailTv=findViewById(R.id.emailOneConsultantTvId);
        aboutNameTv=findViewById(R.id.aboutNameOneConsaluntantTvId);
        aboutTv=findViewById(R.id.aboutDetailsoneConsultantTvId);
        genderTv=findViewById(R.id.genderOneConsultantTvId);
        bloodTv=findViewById(R.id.bloodOneConsultantTvId);
        dateTv=findViewById(R.id.dateOfBirthOneConsultantTvId);
        imageView=findViewById(R.id.imageOneConsultntImageViewid);
        button=findViewById(R.id.backToHomeOneConsultantBtnId);

        Intent intent=getIntent();
        String name,phone,expert,aboutName,about,gender,blood,date,image,email;
        name=intent.getStringExtra("name");
        phone=intent.getStringExtra("phone");
        email=intent.getStringExtra("email");
        expert=intent.getStringExtra("workbackground");
        aboutName="About "+name;
        about=intent.getStringExtra("about");
        gender="Gender          : "+intent.getStringExtra("gender");
        blood= "Blood Group     : "+intent.getStringExtra("bloodgroup");
        date=  "Date of Birth   : "+intent.getStringExtra("date");
        image=intent.getStringExtra("image");
        nameTv.setText(name);
        phoneTv.setText(phone);
        expertTv.setText(expert);
        aboutNameTv.setText(aboutName);
        aboutTv.setText(about);
        genderTv.setText(gender);
        bloodTv.setText(blood);
        dateTv.setText(date);
        emailTv.setText(email);
        Picasso.get().load(image).into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String subject="Mohammad Sakib";
                String body="Feel free to ask any question.";
                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,body);

                startActivity(Intent.createChooser(i,"Share with your Friend"));
            }
        });

    }
}