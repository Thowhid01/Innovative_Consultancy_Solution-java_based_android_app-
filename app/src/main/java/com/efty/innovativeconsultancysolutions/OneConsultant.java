
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class OneConsultant extends AppCompatActivity {

    private TextView nameTv,expertTv,phoneTv,aboutNameTv,aboutTv,genderTv,bloodTv,dateTv,emailTv;
    private ImageView imageView;
    private Button button,blog,video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_consultant);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("OneConsultant !!");

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#25593E"));
        actionBar.setBackgroundDrawable(colorDrawable);



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
        blog=findViewById(R.id.blogOneConsultantTvId);
        video=findViewById(R.id.videoOneConsultantTvId);

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
               try {

                   String to=email.toString();
             /*   String subject="About learning pupuse only !!";
                String message="Sorry for that";
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));*/
                   Intent intent1=new Intent(OneConsultant.this,consultationDetails.class);
                   intent1.putExtra("cemail",to);
                   intent1.putExtra("ctype",expert.toString().trim());
                   intent1.putExtra("cname",name.toString().trim());
                   intent1.putExtra("cphone",phone.toString().trim());
                   startActivity(intent1);

               }catch (Exception e){
                   Toast.makeText(OneConsultant.this, "Problem : "+e, Toast.LENGTH_SHORT).show();
               }


            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent intent=getIntent();
               String email1=emailTv.getText().toString();
               // email=intent.getStringExtra("email");
                Intent intent1=new Intent(OneConsultant.this,singleConsultantAllBlog.class);
                intent1.putExtra("email",email1);
                startActivity(intent1);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{
               //   Intent intent2=getIntent();
               //    String email;
               //    email=intent2.getStringExtra("email").toString();
                   Intent intent3=new Intent(OneConsultant.this,SingleConsultantAllVideoShow.class);
                   intent3.putExtra("email",email.toString());
                   startActivity(intent3);
                 //  Toast.makeText(OneConsultant.this, "Update very soon !!", Toast.LENGTH_SHORT).show();

               }
               catch (Exception e){
                   Toast.makeText(OneConsultant.this, "Problem : "+e+"Waiting for Update !!! coming soon", Toast.LENGTH_SHORT).show();
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