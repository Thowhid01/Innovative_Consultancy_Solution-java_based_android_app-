package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class information_setup extends AppCompatActivity {
private EditText namedittext,phoneedittext,birthdateedittext,workbackgroundedittext,bloodgroupedittext;
private RadioGroup radioGroup;
private RadioButton genderradiobutton;
private Button savebutton,imageuploadbutton;
FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;
Information information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_setup);
        Intent intent=getIntent();
     //   String catagory=intent.getStringExtra("select");
        String catagory="consultant";
        Toast.makeText(information_setup.this, "catagory : "+catagory, Toast.LENGTH_SHORT).show();
        namedittext=findViewById(R.id.informationsetupedittextnameid);
        phoneedittext=findViewById(R.id.informationsetupedittextphoneid);
        birthdateedittext=findViewById(R.id.informationsetupedittextdateid);
        workbackgroundedittext=findViewById(R.id.informationsetupedittextbloodgroupid);
        radioGroup=findViewById(R.id.informationsetuperadiogroupid);
        savebutton=findViewById(R.id.informationsetupupdatebuttonid);
        imageuploadbutton=findViewById(R.id.informationsetupprofileimageid);
        //datasetupwithfirebase
       // firebaseDatabase=FirebaseDatabase.getInstance();
        if(catagory=="consultant"){

            information=new Information();
            savebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name=namedittext.getText().toString();
                    String phone=phoneedittext.getText().toString();
                    String date=birthdateedittext.getText().toString();
                    String work_background=workbackgroundedittext.getText().toString();
                   String gender="male";
                   String bloodgroup=bloodgroupedittext.getText().toString();
                   if (TextUtils.isEmpty(name)&&TextUtils.isEmpty(phone)&&TextUtils.isEmpty(date)&&TextUtils.isEmpty(work_background)
                           &&TextUtils.isEmpty(gender)&&TextUtils.isEmpty(bloodgroup)){
                       Toast.makeText(information_setup.this, "Please Add Some Data ", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       addDatafirebase(name,date,phone,work_background,bloodgroup,gender,catagory);
                   }
                }
            });
        }

    }

    private void addDatafirebase(String name, String date, String phone, String work_background, String bloodgroup, String gender,String catagory) {
        information.setName(name);
        information.setDate(date);
        information.setPhone(phone);
        information.setWorkbackground(work_background);
        information.setBloodgroup(bloodgroup);
        information.setGender(gender);
        databaseReference=FirebaseDatabase.getInstance().getReference(catagory);
        String key=databaseReference.push().getKey();
        databaseReference.child(key).setValue(information);
    }


}


