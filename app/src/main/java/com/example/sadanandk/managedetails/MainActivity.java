package com.example.sadanandk.managedetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    TextView tv;
    DatabaseReference ref;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.etEmail);
        et2 = (EditText) findViewById(R.id.etAdd);
        tv = (TextView) findViewById(R.id.tvShowProfile);
        ref = FirebaseDatabase.getInstance().getReference("users");
    }
    public void save(View v)
    {
        String email = et1.getText().toString().trim();
        String add = et2.getText().toString().trim();
         userId = ref.push().getKey();
        UserDetails u = new UserDetails(email,add);
        ref.child(userId).setValue(u);
       // ref.child(userId).setValue(add);

    }
    public void read(View v)
    {
        ref.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDetails user = dataSnapshot.getValue(UserDetails.class);
                tv.setText(user.add+"\n"+user.email);
                if(user==null)
                {
                    Toast.makeText(MainActivity.this, "Data not fund ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Toast.makeText(MainActivity.this, user.add+"\n"+user.email, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void update(View v)
   {
//        String email = "Sadanand@gmail.com";
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        //Toast.makeText(this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
//         ref.child(userId).child("email").setValue(email);

          Intent i = new Intent(this,UpdateDetails.class);
           i.putExtra("userId",userId);
           startActivity(i);
    }
}
