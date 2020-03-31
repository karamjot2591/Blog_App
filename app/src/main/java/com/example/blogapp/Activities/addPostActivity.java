package com.example.blogapp.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.blogapp.Model.Blog;
import com.example.blogapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addPostActivity extends AppCompatActivity {

    private ImageButton mPostimagebutton;
    private EditText mPosttext;
    private EditText mPostDescription;
    private Button mPostsubmit;
     private DatabaseReference mPostDatabase;
     private FirebaseAuth mAuth;
     private FirebaseUser muser;

     private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        // progress dialog  initial
        mProgress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();

        mPostDatabase = FirebaseDatabase.getInstance().getReference().child("myblog");

        mPostimagebutton = (ImageButton) findViewById(R.id.imageButton);
        mPosttext = (EditText) findViewById(R.id.posttitleid);
        mPostDescription = (EditText) findViewById(R.id.descriptionpostid);
        mPostsubmit = (Button) findViewById(R.id.submitbtn);



        String titlevalue = mPosttext.getText().toString().trim();
        String descriptionvalue = mPostDescription.getText().toString().trim();

        if (TextUtils.isEmpty(titlevalue) && TextUtils.isEmpty(descriptionvalue)) {
            // start uploading here...

            final Blog blog = new Blog("title", "description", "image", "timestamp", "userid");

            mPostsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // posting into database

                    mProgress.setMessage("Posting to database.......");
                    mProgress.show();
                    mPostDatabase.setValue(blog).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_LONG).show();
                            mProgress.dismiss();
                        }
                    });




                }
            });
        }


    }}
