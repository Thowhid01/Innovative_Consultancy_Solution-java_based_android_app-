package com.efty.innovativeconsultancysolutions;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyVideoAdapter extends RecyclerView.Adapter<MyVideoAdapter.ViewHolder> {

    private ArrayList<Video> videoData;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView videoCardview;
        public TextView videoTitleTextView;
        public VideoView videoView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.videoCardview=(CardView)itemView.findViewById(R.id.videoCardViewId);
            this.videoTitleTextView=(TextView) itemView.findViewById(R.id.videotitletextVideoId);
            this.videoView=(VideoView) itemView.findViewById(R.id.videoVideoViewId);
        }
    }


    public MyVideoAdapter(ArrayList<Video> videoData, Context context) {
        this.videoData = videoData;
        this.context=context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View viewVideo = layoutInflater.inflate(R.layout.video_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(viewVideo);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Video videoListData=videoData.get(position);
        holder.videoTitleTextView.setText(videoData.get(position).getTitle());
        String message = videoData.get(position).getVideolink();
        Uri uri=Uri.parse(message);
        holder.videoView.setVideoURI(uri);
      //  holder.videoView.start();
        MediaController mediaController = new MediaController(holder.itemView.getContext());
        holder.videoView.setMediaController(mediaController);
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

            }
        });

        holder.videoCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Video title : "+videoData.get(holder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoData.size();
    }


}
