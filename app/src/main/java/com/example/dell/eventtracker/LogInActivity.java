package com.example.dell.eventtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    EditText emailET;
    EditText passwordET;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_log);
        emailET= (EditText) findViewById(R.id.emailValue);
        passwordET= (EditText) findViewById(R.id.passwordValue);
        firebaseAuth=FirebaseAuth.getInstance();

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Toast.makeText(LogInActivity.this, ""+firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LogInActivity.this,EventActivity.class);
                    startActivity(intent);
                }

            }
        };
    }

    @Override
    protected void onPause() {
        firebaseAuth.removeAuthStateListener(authStateListener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void moveToSignup(View view) {
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email=emailET.getText().toString();
        String password=passwordET.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Toast.makeText(LogInActivity.this, "logged in "+firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LogInActivity.this,EventActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LogInActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
