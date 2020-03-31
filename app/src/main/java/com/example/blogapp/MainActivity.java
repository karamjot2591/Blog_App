package com.example.blogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blogapp.Activities.postlinkactivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthlistner;
    private Button createAccButton;
    private Button loginButton;
    private EditText Emailtext;
    private EditText Passwordtxt;
    private String TAG ="message";
    // creating a user for authoring
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        createAccButton = (Button) findViewById(R.id.createAcc);
        loginButton = (Button) findViewById(R.id.loginbtn);
        Emailtext = (EditText) findViewById(R.id.emailid);
        Passwordtxt = (EditText) findViewById(R.id.passwordid);

        // intance a firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // mauth listner


        mAuthlistner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // user object here
                mUser = firebaseAuth.getCurrentUser();

                if (mUser != null) {
                    Toast.makeText(MainActivity.this, "Signed In ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, postlinkactivity.class));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, " Not Signed In ", Toast.LENGTH_SHORT).show();
                }


            }


        };
// login click activtiy here.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(Emailtext.getText().toString())
                        && !TextUtils.isEmpty(Passwordtxt.getText().toString())){

                    String email = Emailtext.getText().toString();
                    String password = Passwordtxt.getText().toString();

                    // method for login to user


                    login(email,password);


                }
                else{


                }
            }
        });
    }

    private void login(String email, String password) {

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "sign in",Toast.LENGTH_LONG).show();
                            Log.d(TAG, "SIGNinwith email : successful;");

                            // starting new activity here
                            startActivity(new Intent(MainActivity.this, postlinkactivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"login not successful",Toast.LENGTH_LONG).show();
                            Log.d(TAG, "SIGNinwith email : unsuccessful;");
                        }
                    }
                });
    }
    // method for signout users
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==R.id.action_signout){
            mAuth.signOut();
        }

        return super.onOptionsItemSelected(item);
    }
    // method for creating menu items like signout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // on start  overide method
    @Override
    protected void onStart () {
        super.onStart();
        // adding mauth for activity state listner
        mAuth.addAuthStateListener(mAuthlistner);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mAuthlistner!= null){
            mAuth.removeAuthStateListener(mAuthlistner);
        }
    }
};
