package com.example.dell.eventtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText emailET;
    EditText passwordET;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        emailET= (EditText) findViewById(R.id.emailET);
        passwordET= (EditText) findViewById(R.id.passwordET);
        firebaseAuth=FirebaseAuth.getInstance();

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Toast.makeText(SignUpActivity.this, ""+firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUpActivity.this,LogInActivity.class);
                    startActivity(intent);
                }

            }
        };
    }

    public void signup(View view) {
        String email=emailET.getText().toString();
        String password=passwordET.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(SignUpActivity.this, ""+task.getResult(), Toast.LENGTH_SHORT).show();
                emailET.setText(" ");
                passwordET.setText(" ");
                startActivity(new Intent(SignUpActivity.this,LogInActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, "Accout creation failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("account",e.getMessage());
            }
        });
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

    public void moveToLogin(View view) {
        Intent intent=new Intent(this,LogInActivity.class);
        startActivity(intent);
    }
}
