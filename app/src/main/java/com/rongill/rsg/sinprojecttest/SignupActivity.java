package com.rongill.rsg.sinprojecttest;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private EditText email, confirmEmail, password, confirmPassword;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText)findViewById(R.id.email);
        confirmEmail = (EditText)findViewById(R.id.confirm_email);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.confirm_password);

        mFirebaseAuth = FirebaseAuth.getInstance();

    }

    public void signUp(View v){
        if(validateEmailAndPassword()){
            mFirebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "createUserWithEmail:success");
                                updateUI(true);

                            }
                            else{
                                Log.d(TAG, "createUserWithEmail:failed");
                                updateUI(false);
                            }
                        }
                    });

        }else{
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show();
        }
    }

    public void updateUI(Boolean state){
        if(state){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else{
            Toast.makeText(this, "Sign-up failed", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateEmailAndPassword(){
        if(email.getText().toString().equals(confirmEmail.getText().toString())
                && password.getText().toString().equals(confirmPassword.getText().toString()))
            return true;
        return false;
    }
}
