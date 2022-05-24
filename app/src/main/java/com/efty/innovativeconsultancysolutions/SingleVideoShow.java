package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class SingleVideoShow extends AppCompatActivity {

    private TextView titleTv,emailTv,categoryTv,videoDetailsTv;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_video_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("SingleVideoShow !!");
        getSupportActionBar().setSubtitle("Hi..");

        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        String videoLink=intent.getStringExtra("videolink");
        String title=intent.getStringExtra("title");
        String category=intent.getStringExtra("category");
        String thumbnail=intent.getStringExtra("thumbnail");
        String videoDetails=intent.getStringExtra("videoDetails");
        String keyword=intent.getStringExtra("keyword");

        titleTv=findViewById(R.id.videoTitleTvId);
        emailTv=findViewById(R.id.videoEmailTvId);
        categoryTv=findViewById(R.id.videoCategoryTvId);
        videoDetailsTv=findViewById(R.id.videoDetailsTvId);
        videoView=findViewById(R.id.singleVideoVideoViewId);
        titleTv.setText(title);
        emailTv.setText(email);
        categoryTv.setText(category);
        videoDetailsTv.setText(videoDetails);
        Uri uri=Uri.parse(videoLink);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

            }
        });






        Toast.makeText(SingleVideoShow.this, "Email : "+email+"  VideoLink : "+videoLink, Toast.LENGTH_SHORT).show();

    }
}