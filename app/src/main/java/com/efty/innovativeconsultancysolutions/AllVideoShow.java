package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllVideoShow extends AppCompatActivity {

    ArrayList<Video> videoItemData;
    RecyclerView videoRecyclerView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_video_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("AllVideoShow !!");
        getSupportActionBar().setSubtitle("Hi..");

        videoItemData=new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        videoRecyclerView=findViewById(R.id.videoShowActivityRecyclerviewId);
        MyVideoAdapter adapter=new MyVideoAdapter(videoItemData,getApplicationContext());
        videoRecyclerView.setHasFixedSize(true);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        videoRecyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("Video").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    videoItemData.clear();
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        Video video=snapshot1.getValue(Video.class);
                        videoItemData.add(new Video(video.getCatagory(),video.getKeyword(),video.getThumbnail(),video.getTitle(),video.getVideoDetails(),video.getVideolink(),video.getEmail()));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}