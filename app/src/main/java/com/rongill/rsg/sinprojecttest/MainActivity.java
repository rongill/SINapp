package com.rongill.rsg.sinprojecttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;
    private TextView mUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mAuth.getCurrentUser();
        mUsername = (TextView)findViewById(R.id.user_name_TV);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else{
            mUsername.setText(user.getDisplayName());
        }
    }

    public void signOut(View v){
        mAuth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }
}
