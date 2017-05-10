package com.example.sadanandk.managedetails;

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

public class Login extends AppCompatActivity {
EditText et1,et2;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1 = (EditText) findViewById(R.id.login);
        et2 = (EditText) findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
    }
    public void login(View v)
    {
        String email = et1.getText().toString().trim();
        String pass = et2.getText().toString().trim();
        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,MainActivity.class));
                }
            }
        });
    }
}
