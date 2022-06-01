package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class singleConsultantAllBlog extends AppCompatActivity {

    ArrayList<Blog> blogItemData;
    RecyclerView blogsingleConsultantRecyclerView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_consultant_all_blog);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("singleConsultantAllBlog");

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#25593E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        Intent intent=getIntent();
        String email = intent.getStringExtra("email");
        Toast.makeText(singleConsultantAllBlog.this, "Consultant : "+email, Toast.LENGTH_SHORT).show();

        blogItemData=new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        blogsingleConsultantRecyclerView=findViewById(R.id.singleConsultantAllBlogRecyclerViewId);
        MyBlogAdapter adapter=new MyBlogAdapter(blogItemData,getApplicationContext());
        blogsingleConsultantRecyclerView.setHasFixedSize(true);
        blogsingleConsultantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        blogsingleConsultantRecyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("Blogs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    blogItemData.clear();
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        Blog blog=snapshot1.getValue(Blog.class);
                        if (blog.getEmail().equals(email)){
                            blogItemData.add(new Blog(blog.getBody(),blog.getCatahgory(),blog.getEmail(),blog.getImage(),blog.getKeyword(),blog.getTitle()));
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
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