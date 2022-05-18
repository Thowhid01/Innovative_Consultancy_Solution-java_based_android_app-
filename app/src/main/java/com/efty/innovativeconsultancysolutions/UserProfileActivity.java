package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UserProfileActivity extends AppCompatActivity {

    private ImageView imageUserImageView;
    private TextView nameUserTv,expertUserTv,phoneUserTv,emailUserTv,aboutNameUserTv,aboutDetailsUserTv,genderUserTv;
    private TextView bloodUserTv,dateOfBirthUserTv;
    private Button backToHomeUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

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
        String email=intent.getStringExtra("email").toString();
        try{

            FirebaseDatabase.getInstance().getReference().child("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            User user=snapshot1.getValue(User.class);
                            if(user.getEmail()==email){
                                Picasso.get().load(user.getImage()).into(imageUserImageView);
                                nameUserTv.setText(user.getName());
                                expertUserTv.setText(user.getWorkbackground());
                                phoneUserTv.setText(user.getPhone());
                                emailUserTv.setText(user.getEmail());
                                aboutNameUserTv.setText("About "+user.getName());
                                aboutDetailsUserTv.setText(user.getAbout());
                                genderUserTv.setText("Gender : "+user.getGender());
                                bloodUserTv.setText("Blood Group : "+user.getBloodgroup());
                                dateOfBirthUserTv.setText("Date of Birth : "+user.getDate());
                            }
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        catch (Exception e){
            Toast.makeText(UserProfileActivity.this, "Problem :"+e, Toast.LENGTH_SHORT).show();
        }


    }
}