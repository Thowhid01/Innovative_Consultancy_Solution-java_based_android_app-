package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SingleConsultantAllVideoShow extends AppCompatActivity {

    ArrayList<Video> videoItemData;
    RecyclerView videosingleConsultantRecyclerView;
    FirebaseAuth mAuth;
    String email;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_consultant_all_video_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SingleConsultantAllVideoShow");
        getSupportActionBar().setSubtitle("Hi...");
        try {
        videosingleConsultantRecyclerView=findViewById(R.id.singleConsultantAllVideoRecyclerViewId);
        intent=getIntent();
        email = intent.getStringExtra("email");
        Toast.makeText(SingleConsultantAllVideoShow.this, "Consultant Email : "+email, Toast.LENGTH_SHORT).show();
        videoItemData=new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
    //    videosingleConsultantRecyclerView=findViewById(R.id.singleConsultantAllVideoRecyclerViewId);
        MyVideoAdapter adapter=new MyVideoAdapter(videoItemData,getApplicationContext());
        videosingleConsultantRecyclerView.setHasFixedSize(true);
        videosingleConsultantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        videosingleConsultantRecyclerView.setAdapter(adapter);





           FirebaseDatabase.getInstance().getReference().child("Video").addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.exists()){
                       videoItemData.clear();
                       for(DataSnapshot snapshot1:snapshot.getChildren()){
                           Video video=snapshot1.getValue(Video.class);
                          if(video.getEmail().equals(email)){
                               videoItemData.add(new Video(video.getCatagory(),video.getKeyword(),video.getThumbnail(),video.getTitle(),video.getVideoDetails(),video.getVideolink(),video.getEmail()));
                           }
                       }
                       adapter.notifyDataSetChanged();
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });


       }catch (Exception e){
           Toast.makeText(SingleConsultantAllVideoShow.this, "Problem : "+e+"Consultant : "+email, Toast.LENGTH_SHORT).show();
       }



    }
}