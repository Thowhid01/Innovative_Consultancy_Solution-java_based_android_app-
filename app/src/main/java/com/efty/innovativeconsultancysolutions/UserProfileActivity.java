package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("UserProfileActivity !!");
        getSupportActionBar().setSubtitle("Hi...");

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
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        try{
            FirebaseDatabase.getInstance().getReference().child("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for (DataSnapshot s:snapshot.getChildren()){
                            User u=s.getValue(User.class);
                            if (u.getEmail().equals(email)){
                                userArrayList.add(u);
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
                            }

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





        }
        catch (Exception ee){
            Toast.makeText(UserProfileActivity.this, "Problem : "+ee, Toast.LENGTH_LONG).show();
        }

        backToHomeUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserProfileActivity.this, "Email : "+email, Toast.LENGTH_SHORT).show();

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
