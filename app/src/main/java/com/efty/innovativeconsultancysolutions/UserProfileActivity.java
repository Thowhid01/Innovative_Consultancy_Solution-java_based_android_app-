package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {

    private ImageView imageUserImageView;
    private TextView nameUserTv,expertUserTv,phoneUserTv,emailUserTv,aboutNameUserTv,aboutDetailsUserTv,genderUserTv;
    private TextView bloodUserTv,dateOfBirthUserTv;
    private Button backToHomeUserBtn;
    String email;
    ArrayList<User> userArrayList=new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
       // this.setTitle("Hi User");

        imageUserImageView=findViewById(R.id.imageUserImageViewid);
        nameUserTv=findViewById(R.id.nameUserTvId);
        expertUserTv=findViewById(R.id.expertUserTvId);
        phoneUserTv=findViewById(R.id.phoneUserTvId);
        emailUserTv=findViewById(R.id.emailUserTvId);
        aboutNameUserTv=findViewById(R.id.aboutNameUserTvId);
        aboutDetailsUserTv=findViewById(R.id.aboutDetailsUserTvId);
        genderUserTv=findViewById(R.id.genderUserTvId);
        bloodUserTv=findViewById(R.id.bloodUserTvId);
        dateOfBirthUserTv=findViewById(R.id.dateOfBirthUserTvId);
        backToHomeUserBtn=findViewById(R.id.backToHomeUserBtnId);


            FirebaseDatabase.getInstance().getReference().child("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                       // Toast.makeText(UserProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        for (DataSnapshot s:snapshot.getChildren()){
                            User u=s.getValue(User.class);
                            Picasso.get().load(u.getImage()).into(imageUserImageView);
                            nameUserTv.setText(u.getName());
                            expertUserTv.setText(u.getWorkbackground());
                            phoneUserTv.setText(u.getPhone());
                            emailUserTv.setText(u.getEmail());
                            aboutNameUserTv.setText("About "+u.getName());
                            aboutDetailsUserTv.setText(u.getAbout());
                            genderUserTv.setText("Gender   : "+u.getGender());
                            bloodUserTv.setText("Blood Group : "+u.getBloodgroup());
                            dateOfBirthUserTv.setText("Date Of Birth : "+u.getDate());
                            Toast.makeText(UserProfileActivity.this, "Data set in user profile", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            backToHomeUserBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });






        }


    }
