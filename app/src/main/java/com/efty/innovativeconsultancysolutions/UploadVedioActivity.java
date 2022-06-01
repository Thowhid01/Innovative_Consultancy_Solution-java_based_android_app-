package com.efty.innovativeconsultancysolutions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class UploadVedioActivity extends AppCompatActivity {

    private EditText videoInfoEditText,videoTitleEditText,videothumbnailEditText,videocatagoryEditText,videodurationEditText;
    private TextView videoUploadTextView;
    private Button videopublishButton;
    Intent intent;
    String email;
    HashMap<String,Object> hashMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_vedio);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("UploadVedioActivity !!");

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#25593E"));
        actionBar.setBackgroundDrawable(colorDrawable);

        videoInfoEditText=findViewById(R.id.videoinfoTextViewId);
        videoTitleEditText=findViewById(R.id.videotitleEditTextId);
        videothumbnailEditText=findViewById(R.id.videothumbnailEditTextId);
        videocatagoryEditText=findViewById(R.id.videocatagoryEditTextId);
        videodurationEditText=findViewById(R.id.videodurationEditTextId);
        videoUploadTextView=findViewById(R.id.videoUploadTextViewId);
        videopublishButton=findViewById(R.id.videopublishButtonId);


        intent=getIntent();
        email=intent.getStringExtra("email");
        getSupportActionBar().setSubtitle("Hi "+email);

        videoUploadTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    choosevideo();
            }
        });

        videopublishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    hashMap.put("videoDetails",videoInfoEditText.getText().toString());
                    hashMap.put("title",videoTitleEditText.getText().toString());
                    hashMap.put("thumbnail",videothumbnailEditText.getText().toString());
                    hashMap.put("catagory",videocatagoryEditText.getText().toString());
                    hashMap.put("keyword",videodurationEditText.getText().toString());
                    hashMap.put("email",email);

                    Thread.sleep(3000);
                    uploadvideo();

                    Toast.makeText(UploadVedioActivity.this, "Data set and video upload successful !!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(UploadVedioActivity.this,ConsultantBolgVedioSelectActivity.class));

                }catch (Exception e){
                    Toast.makeText(UploadVedioActivity.this, "Problem in Uploadvideo : "+e, Toast.LENGTH_SHORT).show();
                }
            }
        });


        Toast.makeText(UploadVedioActivity.this, "Upload vedio : "+email, Toast.LENGTH_SHORT).show();

    }

    // choose a video from phone storage
    private void choosevideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 5);
    }

    Uri videouri;

    // startActivityForResult is used to receive the result, which is the selected video.
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            videouri = data.getData();
        }
    }

    private String getfiletype(Uri videouri) {
        ContentResolver r = getContentResolver();
        // get the file type ,in this case its mp4
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(r.getType(videouri));
    }

    private void uploadvideo() {
        if (videouri != null) {
            // save the selected video in Firebase storage
            final StorageReference reference = FirebaseStorage.getInstance().getReference("Videos/" + System.currentTimeMillis() + "." + getfiletype(videouri));
            reference.putFile(videouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful()) ;
                    // get the link of video
                    String downloadUri = uriTask.getResult().toString();
                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Video");
                    hashMap.put("videolink", downloadUri);
                    reference1.child("" + System.currentTimeMillis()).setValue(hashMap);
                    // Video uploaded successfully
                    Toast.makeText(UploadVedioActivity.this, "Video Uploaded!!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Error, Image not uploaded
                    Toast.makeText(UploadVedioActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                // Progress Listener for loading
                // percentage on the dialog box
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    // show the progress bar
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    Toast.makeText(UploadVedioActivity.this, "Uploaded " + (int)progress + "%" , Toast.LENGTH_SHORT).show();
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