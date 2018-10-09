package com.rongill.rsg.sinprojecttest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login";
    private FirebaseAuth mFirebaseAuth;
    private EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Edit text init-

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        // Firebase shared instance init-
        mFirebaseAuth = FirebaseAuth.getInstance();

    }

    public void loginOrSignup(View v){

        if( !email.getText().toString().equals("") ){
            if (password.getText().toString().equals(""))
                Toast.makeText(this, "please enter a password", Toast.LENGTH_LONG).show();
            else{
                mFirebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Log.d(TAG, "signInWithEmail:success");
                                    updateUI(true);

                                }else{
                                    Log.w(TAG,"signInWithEmail:failed");
                                    updateUI(false);
                                }

                            }
                        });
            }

        }else{
            startActivity(new Intent(this,SignupActivity.class));
            finish();

        }

    }

    private void updateUI(boolean state){
        if (state){
            startActivity(new Intent(this, MainActivity.class));

        }else{
            Toast.makeText(this, "Authentiacation failed",Toast.LENGTH_LONG).show();
            email.setText("");
            password.setText("");
        }

    }

    public void forgotPasswordRequest(View v){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
}
