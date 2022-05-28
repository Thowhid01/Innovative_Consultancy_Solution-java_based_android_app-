package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class consultantClientDetails extends AppCompatActivity {

    ArrayList<Client> clientData;
    RecyclerView clientRecyclerView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_client_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("consultantClientDetails !!");

        Intent intent=getIntent();
        String email=intent.getStringExtra("email").toString();
        getSupportActionBar().setSubtitle(email);

        clientData=new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        clientRecyclerView=findViewById(R.id.clientDetailsRecyclerViewId);
        MyClientAdapter adapter=new MyClientAdapter(clientData,getApplicationContext());
        clientRecyclerView.setHasFixedSize(true);
        clientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        clientRecyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("ConsultationDetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    clientData.clear();
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        Client client=snapshot1.getValue(Client.class);
                        if(client.getConsultantEmail().equals(email)){
                            clientData.add(new Client(client.getConsultantEmail(),client.getConsultantName(),client.getConsultantPhone(),client.getConsultantType(),client.getConsultatDetails(),client.getConsultationDate(),client.getUserEmail(),client.getUserName()));
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
}