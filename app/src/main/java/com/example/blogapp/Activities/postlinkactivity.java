package com.example.blogapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.blogapp.MainActivity;
import com.example.blogapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class postlinkactivity extends AppCompatActivity {
private FirebaseDatabase mDatabase;
private DatabaseReference mDatabasereference;
private FirebaseUser mUser;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlinkactivity);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabasereference = mDatabase.getReference().child("myblog");
        mDatabasereference.keepSynced(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                if (mUser!= null && mAuth!= null){
                    startActivity(new Intent(postlinkactivity.this,addPostActivity.class));
                    finish();
                }
                break;
            case R.id.action_signout:

                if (mUser!= null && mAuth!= null){
                    mAuth.signOut();
                    startActivity(new Intent(postlinkactivity.this, MainActivity.class));
                    finish();
                }
        }

        return super.onOptionsItemSelected(item);
    }
}
