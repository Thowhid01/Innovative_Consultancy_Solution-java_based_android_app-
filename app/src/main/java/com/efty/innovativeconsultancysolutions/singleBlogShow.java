package com.efty.innovativeconsultancysolutions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class singleBlogShow extends AppCompatActivity {

    private TextView titleTextView,catagoryTextView,emailTextView,keywordTextView,bodyTextView;
    private ImageView blogImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_blog_show);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("singleBlogShow !!");
        getSupportActionBar().setSubtitle("Hi..");

        titleTextView=findViewById(R.id.titleSingleBlogTvId);
        catagoryTextView=findViewById(R.id.catagorySingleBlogTvId);
        emailTextView=findViewById(R.id.emailSingleBlogTvId);
        keywordTextView=findViewById(R.id.keywordSingleBlogTvId);
        bodyTextView=findViewById(R.id.bodySingleBlogTvId);
        blogImageView=findViewById(R.id.singleblogImageViewId);

        Intent intent=getIntent();
        String body,title,category,email,keyword,image;
        body=intent.getStringExtra("body");
        title=intent.getStringExtra("title");
        category="Category    "+intent.getStringExtra("category");
        email="Published By "+intent.getStringExtra("email");
        keyword="Keyword   "+intent.getStringExtra("keyword");
        image=intent.getStringExtra("image");
        titleTextView.setText(title);
        catagoryTextView.setText(category);
        emailTextView.setText(email);
        keywordTextView.setText(keyword);
        bodyTextView.setText(body);
        Picasso.get().load(image).into(blogImageView);

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