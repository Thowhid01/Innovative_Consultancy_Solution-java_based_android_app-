package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.UUID;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class information_setup extends AppCompatActivity {
    private EditText name,phone,date,workbackground,blood,about,email;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private Button updateBtn,imageUploadBtn;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage;
    StorageReference storageReference;
    String imageUrl;
    Integer catagory;
    HashMap<String,Object> hashMap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_setup);
        Intent intent=getIntent();
       catagory= intent.getIntExtra("select",0);
       updateBtn=findViewById(R.id.informationsetupupdatebuttonid);
       imageUploadBtn=findViewById(R.id.informationsetupuploadimagebuttonid);
       radioGroup=findViewById(R.id.informationsetradiogenderid);
       name=findViewById(R.id.informationsetupedittextnameid);
       phone=findViewById(R.id.informationsetupedittextphoneid);
       email=findViewById(R.id.informationsetupedittextemailid);
       email.setText(intent.getStringExtra("email"));
       date=findViewById(R.id.informationsetupedittextdateid);
       workbackground=findViewById(R.id.informationsetupedittextworkbackgroundid);
       blood=findViewById(R.id.informationsetupedittextbloodgroupid);
       about=findViewById(R.id.informationsetupedittextaboutid);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        imageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });
        
       updateBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               int selectedID = radioGroup.getCheckedRadioButtonId();
               radioButton = findViewById(selectedID);
               try{
               String na=name.getText().toString();
               String ph=phone.getText().toString();
               String da=date.getText().toString();
               String work=workbackground.getText().toString();
               String bl=blood.getText().toString();
               String gender=radioButton.getText().toString();
               String ab=about.getText().toString();
               String em= intent.getStringExtra("email");
               hashMap.put("name",na);
               hashMap.put("phone",ph);
               hashMap.put("date",da);
               hashMap.put("workbackground",work);
               hashMap.put("bloodgroup",bl);
               hashMap.put("gender",gender);
               hashMap.put("about",ab);
               hashMap.put("email",em);
              // hashMap.put("image","");


                  try{
                      if(catagory==1){
                          uploadImage();
                     //     hashMap.put("image",imageUrl);

                         // FirebaseDatabase.getInstance().getReference().child("Consultant").push().setValue(hashMap);

                          Toast.makeText(information_setup.this, "Data added as a consultant successfully!!", Toast.LENGTH_SHORT).show();
                          Intent in=new Intent(information_setup.this,ConsulanatyHomeActivity.class);
                          startActivity(in);
                      }
                      else if(catagory==2){
                          uploadImage();
                       //   hashMap.put("image",imageUrl);

                        //  FirebaseDatabase.getInstance().getReference().child("User").push().setValue(hashMap);

                          Toast.makeText(information_setup.this, "Data added as a user successfully!!", Toast.LENGTH_SHORT).show();
                          Intent i=new Intent(information_setup.this,UserHomeActivity.class);
                          startActivity(i);
                      }
                      else {
                          Toast.makeText(information_setup.this, "Something went to wrong!!", Toast.LENGTH_SHORT).show();
                      }
                  }catch (Exception e){
                      Toast.makeText(information_setup.this, "Something went to wrong : "+e, Toast.LENGTH_SHORT).show();
                  }

               }
               catch (Exception e){

                   Toast.makeText(information_setup.this, "Something went to wrong : "+e, Toast.LENGTH_SHORT).show();
               }

           }
       });


    }



    private void SelectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            filePath = data.getData();
            imageUrl=data.getData().getPath().toString();
            try {


                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);

            }

            catch (Exception e) {
                Toast.makeText(information_setup.this, "Image Problem : "+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadImage() {
        if (filePath != null) {



            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());
           // imageUrl="gs://innovative-consultancy-882f5.appspot.com/images/";//+UUID.randomUUID().toString();
            //gs://innovative-consultancy-882f5.appspot.com/images/ccd32a0c-aaee-4e17-9a12-aa0c5ffe4ec4
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            imageUrl=uri.toString();

                                            if(catagory==1){
                                                hashMap.put("image",imageUrl);
                                                FirebaseDatabase.getInstance().getReference().child("Consultant").push().setValue(hashMap);
                                            }
                                            else if(catagory==2){
                                                hashMap.put("image",imageUrl);
                                                FirebaseDatabase.getInstance().getReference().child("User").push().setValue(hashMap);
                                            }

                                        }
                                    });



                                    Toast
                                            .makeText(information_setup.this,
                                                    "Image Uploaded!!"+imageUrl,
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            Toast
                                    .makeText(information_setup.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());

                                }
                            });
        }
    }

    }





