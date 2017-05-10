package com.example.sadanandk.managedetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDetails extends AppCompatActivity {
    EditText et1;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        et1 = (EditText) findViewById(R.id.newEmail);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        userId = b.getString("userId");
    }
    public  void update(View v)
    {
        String email = et1.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
//        //Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
        ref.child(userId).child("email").setValue(email);
    }
    public void delete(View v)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.child(userId).removeValue();
    }
}
