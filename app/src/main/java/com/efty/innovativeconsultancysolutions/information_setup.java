package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
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
    Intent intent1;
    HashMap<String,Object> hashMap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_setup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("information_setup");
        getSupportActionBar().setSubtitle("Hi...");
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

                          Intent intent1=new Intent(information_setup.this,ConsultantBolgVedioSelectActivity.class);
                          intent1.putExtra("email",em);
                          startActivity(intent1);
                      }
                      else if(catagory==2){
                          uploadImage();
                       //   hashMap.put("image",imageUrl);

                        //  FirebaseDatabase.getInstance().getReference().child("User").push().setValue(hashMap);

                          Toast.makeText(information_setup.this, "Data added as a user successfully!!", Toast.LENGTH_SHORT).show();
                          intent1=new Intent(information_setup.this,UserHomeActivity.class);
                          intent1.putExtra("name",na);
                          intent1.putExtra("phone",ph);
                          intent1.putExtra("date",da);
                          intent1.putExtra("workbackground",work);
                          intent1.putExtra("bloodgroup",bl);
                          intent1.putExtra("gender",gender);
                          intent1.putExtra("about",ab);
                          intent1.putExtra("email",em);


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
                                                intent1.putExtra("image",imageUrl);
                                                startActivity(intent1);
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





