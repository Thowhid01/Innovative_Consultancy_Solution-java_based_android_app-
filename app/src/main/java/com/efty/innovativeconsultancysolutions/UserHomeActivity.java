package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHomeActivity extends AppCompatActivity {

    ArrayList<Consultant> listItemData;
    RecyclerView recyclerView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Our Experts and UserHomeActivity !!");
        Intent i=getIntent();
        getSupportActionBar().setSubtitle(i.getStringExtra("email"));

        listItemData=new ArrayList<>();

        mAuth=FirebaseAuth.getInstance();
        recyclerView=(RecyclerView) findViewById(R.id.expertDetailsRecylerVtewId);
        MyListAdapter adapter=new MyListAdapter(listItemData,getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("Consultant").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    listItemData.clear();
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        Consultant consultant=snapshot1.getValue(Consultant.class);
                        listItemData.add(new Consultant(consultant.getWorkbackground(),consultant.getName(),consultant.getPhone(),consultant.getImage(),consultant.getAbout(),consultant.getBloodgroup(),consultant.getDate(),consultant.getGender(),consultant.getEmail()));
                    }
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent=new Intent(UserHomeActivity.this,OneConsultant.class);
        String email=i.getStringExtra("email");
        intent.putExtra("uemail",email);
        //startActivity(intent);

    }
    //option menu set


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profileMenuId:

                Intent intent=getIntent();
                String na,ph,da,work,bl,gender,ab,img;
                String email=intent.getStringExtra("email");
                na= intent.getStringExtra("name");
                ph=intent.getStringExtra("phone");
                da=intent.getStringExtra("date");
               work= intent.getStringExtra("workbackground");
               bl= intent.getStringExtra("bloodgroup");
               gender= intent.getStringExtra("gender");
               ab= intent.getStringExtra("about");
               img=intent.getStringExtra("image");
                Intent intent1=new Intent(UserHomeActivity.this,UserProfileActivity.class);
                intent1.putExtra("email",email);
                intent1.putExtra("name",na);
                intent1.putExtra("phone",ph);
                intent1.putExtra("date",da);
                intent1.putExtra("workbackground",work);
                intent1.putExtra("bloodgroup",bl);
                intent1.putExtra("gender",gender);
                intent1.putExtra("about",ab);
                intent1.putExtra("image",img);
                startActivity(intent1);
                Toast.makeText(UserHomeActivity.this, "Profile selected : "+email, Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.blogMenuId:
                Intent intent3=getIntent();
                String email2=intent3.getStringExtra("email");
                Intent intent4=new Intent(UserHomeActivity.this,AllBlogShow.class);
                intent4.putExtra("email",email2);
                startActivity(intent4);
                Toast.makeText(UserHomeActivity.this, "Blog selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.videoMenuId:
                Intent intent5=getIntent();
                String email3=intent5.getStringExtra("email");
                Intent intent6=new Intent(UserHomeActivity.this,AllVideoShow.class);
                intent6.putExtra("email",email3);
                startActivity(intent6);
                return true;

            case  R.id.aboutappMenuId:
                Toast.makeText(UserHomeActivity.this, "About app selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.aboutdevelopersMenuId:
                Toast.makeText(UserHomeActivity.this, "About Developers selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.signoutMenuId:
                Toast.makeText(UserHomeActivity.this, "Sign out selected", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent2=new Intent(UserHomeActivity.this,getting_started.class);
                startActivity(intent2);
                return true;
            case android.R.id.home:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }

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