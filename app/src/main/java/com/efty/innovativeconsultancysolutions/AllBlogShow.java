package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllBlogShow extends AppCompatActivity {

    ArrayList<Blog> blogItemData;
    RecyclerView blogRecyclerView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_blog_show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All blogs and AllBlogShow !!");
        getSupportActionBar().setSubtitle("Hi..");

        blogItemData=new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        blogRecyclerView=findViewById(R.id.blogshowActivityRecyclerViewId);
        MyBlogAdapter adapter=new MyBlogAdapter(blogItemData,getApplicationContext());
        blogRecyclerView.setHasFixedSize(true);
        blogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        blogRecyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("Blogs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    blogItemData.clear();
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        Blog blog=snapshot1.getValue(Blog.class);
                        blogItemData.add(new Blog(blog.getBody(),blog.getCatahgory(),blog.getEmail(),blog.getImage(),blog.getKeyword(),blog.getTitle()));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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