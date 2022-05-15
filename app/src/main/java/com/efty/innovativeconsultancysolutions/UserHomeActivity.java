package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHomeActivity extends AppCompatActivity {

    ArrayList<Consultant> listItemData;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        this.setTitle("Our Experts");
       // this.getActionBar().setIcon(R.drawable.ic_baseline_menu_24);

        listItemData=new ArrayList<>();
     /*   listItemData.add(new ListItemData("03/03/2000","Mohammad Faisal","01878141051"));
        listItemData.add(new ListItemData("03/04/2012","Mohammad Arif","01878141041"));
*/
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
                        listItemData.add(new Consultant(consultant.getWorkbackground(),consultant.getName(),consultant.getPhone(),consultant.getImage(),consultant.getAbout(),consultant.getBloodgroup(),consultant.getDate(),consultant.getGender()));

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
                Toast.makeText(UserHomeActivity.this, "Profile selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.blogMenuId:
                Toast.makeText(UserHomeActivity.this, "Blog selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.aboutappMenuId:
                Toast.makeText(UserHomeActivity.this, "About app selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.aboutdevelopersMenuId:
                Toast.makeText(UserHomeActivity.this, "About Developers selected", Toast.LENGTH_SHORT).show();
                return true;
            case  R.id.signoutMenuId:
                Toast.makeText(UserHomeActivity.this, "Sign out selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}