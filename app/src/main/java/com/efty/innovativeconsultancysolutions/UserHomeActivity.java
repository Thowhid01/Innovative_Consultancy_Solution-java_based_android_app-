package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        getSupportActionBar().setTitle("Our Experts and UserHomeActivity !!");
        Intent i=getIntent();

        getSupportActionBar().setSubtitle(i.getStringExtra("email"));

       // this.getActionBar().setIcon(R.drawable.ic_baseline_menu_24);

        listItemData=new ArrayList<>();
     /*   listItemData.add(new ListItemData("03/03/2000","Mohammad Faisal","01878141051"));
        listItemData.add(new ListItemData("03/04/2012","Mohammad Arif","01878141041"));
*/
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
                String email=intent.getStringExtra("email");
                Intent intent1=new Intent(UserHomeActivity.this,UserProfileActivity.class);
                intent1.putExtra("email",email);
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
            case  R.id.aboutappMenuId:
                Toast.makeText(UserHomeActivity.this, "About app selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.aboutdevelopersMenuId:
                Intent intent5=getIntent();
                String email3=intent5.getStringExtra("email");
                Intent intent6=new Intent(UserHomeActivity.this,AllVideoShow.class);
                intent6.putExtra("email",email3);
                startActivity(intent6);
                Toast.makeText(UserHomeActivity.this, "About Developers selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.signoutMenuId:
                Toast.makeText(UserHomeActivity.this, "Sign out selected", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent2=new Intent(UserHomeActivity.this,getting_started.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}