package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class MakeBlogActivity extends AppCompatActivity {

    Intent intent;
    String email;
    private EditText blogBodyTv,titleTv,catagoryTv,keywordTv;
    private Button uploadPhotoBtn,blogPublishBtn;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;
    FirebaseStorage storage;
    StorageReference storageReference;
    String imageUrl;
    HashMap<String,Object> hashMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_blog);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("MakeBlogActivity !!");

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#25593E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        intent=getIntent();
        email=intent.getStringExtra("email");
        getSupportActionBar().setSubtitle("Hi "+email);

        Toast.makeText(MakeBlogActivity.this, "Make Blog : "+email, Toast.LENGTH_SHORT).show();

        blogBodyTv=findViewById(R.id.blogBodyTextViewId);
        titleTv=findViewById(R.id.titleEditTextId);
        catagoryTv=findViewById(R.id.catagoryEditTextId);
        keywordTv=findViewById(R.id.keywordEditTextId);
        uploadPhotoBtn=findViewById(R.id.picUploadButtonId);
        blogPublishBtn=findViewById(R.id.blogpublishButtonId);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        uploadPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    SelectImage();
            }
        });

        blogPublishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    hashMap.put("body",blogBodyTv.getText().toString());
                    hashMap.put("title",titleTv.getText().toString());
                    hashMap.put("catahgory",catagoryTv.getText().toString());
                    hashMap.put("keyword",keywordTv.getText().toString());
                    hashMap.put("email",email);
                    uploadImage();

                    Toast.makeText(MakeBlogActivity.this, "Blog is uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(MakeBlogActivity.this,ConsultantBolgVedioSelectActivity.class));




                }catch (Exception e){
                    Toast.makeText(MakeBlogActivity.this, "Something went to wrong : "+e, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MakeBlogActivity.this, "Image Problem : "+e, Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void uploadImage() {
        if (filePath != null) {



            StorageReference ref
                    = storageReference
                    .child(
                            "blogimages/"
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
                                            hashMap.put("image",imageUrl);
                                            FirebaseDatabase.getInstance().getReference().child("Blogs").push().setValue(hashMap);


                                        }
                                    });



                                    Toast
                                            .makeText(MakeBlogActivity.this,
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
                                    .makeText(MakeBlogActivity.this,
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
            startActivity(new Intent(MakeBlogActivity.this,ConsultantBolgVedioSelectActivity.class));
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
                startActivity(new Intent(MakeBlogActivity.this,ConsultantBolgVedioSelectActivity.class));
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