package com.efty.innovativeconsultancysolutions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyBlogAdapter extends RecyclerView.Adapter<MyBlogAdapter.ViewHolder> {

    private ArrayList<Blog> blogData;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView blogCardview;
        public TextView blogTitleTextView,blogkeywordTextView,blogCatagoryTextView,blogpublisherTextView;
        public ImageView blogImgaView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.blogCardview=(CardView)itemView.findViewById(R.id.blogCardViewId);
            this.blogTitleTextView=(TextView) itemView.findViewById(R.id.titleBloglistTextViewId);
            this.blogkeywordTextView=(TextView) itemView.findViewById(R.id.keywordBlogListTextViewId);
            this.blogCatagoryTextView=(TextView) itemView.findViewById(R.id.catagoryBlogListTextViewId);
            this.blogpublisherTextView=(TextView) itemView.findViewById(R.id.publishedByBlogListTextViewId);
            this.blogImgaView=(ImageView) itemView.findViewById(R.id.bloglistImageViewId);

        }
    }

    public MyBlogAdapter(ArrayList<Blog> blogData, Context context) {
        this.blogData = blogData;
        this.context=context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View blogView = layoutInflater.inflate(R.layout.blog_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(blogView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Blog blogListData=blogData.get(position);
        holder.blogTitleTextView.setText(blogData.get(position).getTitle());
        holder.blogkeywordTextView.setText("Keyword     "+blogData.get(position).getKeyword());
        holder.blogCatagoryTextView.setText("Category    "+blogData.get(position).getCatahgory());
        holder.blogpublisherTextView.setText("Published By "+blogData.get(position).getEmail());
        Picasso.get().load(blogData.get(position).getImage()).into(holder.blogImgaView);
        holder.blogCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),singleBlogShow.class);
                intent.putExtra("body",blogData.get(holder.getAdapterPosition()).getBody());
                intent.putExtra("category",blogData.get(holder.getAdapterPosition()).getCatahgory());
                intent.putExtra("email",blogData.get(holder.getAdapterPosition()).getEmail());
                intent.putExtra("image",blogData.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("keyword",blogData.get(holder.getAdapterPosition()).getKeyword());
                intent.putExtra("title",blogData.get(holder.getAdapterPosition()).getTitle());
                view.getContext().startActivity(intent);
                Toast.makeText(view.getContext(),"Blog title : "+blogData.get(holder.getAdapterPosition()).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return blogData.size();
    }



}
