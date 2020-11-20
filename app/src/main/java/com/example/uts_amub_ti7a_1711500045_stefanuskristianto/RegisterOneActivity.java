package com.example.uts_amub_ti7a_1711500045_stefanuskristianto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneActivity extends AppCompatActivity {

    Button next;
    EditText user, pass, email;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        user = findViewById(R.id.etdUsername);
        pass = findViewById(R.id.etdPassword);
        email = findViewById(R.id.etdEmail);
        next = findViewById(R.id.btnNext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, user.getText().toString());
                editor.apply();

                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(user.getText().toString());

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange (@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("Username").setValue(user.getText().toString());
                        dataSnapshot.getRef().child("Password").setValue(pass.getText().toString());
                        dataSnapshot.getRef().child("E-Mail").setValue(email.getText().toString());
                        dataSnapshot.getRef().child("User Balance").setValue(800);
                    }
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
                Intent gotonexregister = new Intent(RegisterOneActivity.this,RegisterTwoActivity.class);
                startActivity(gotonexregister);
            }


        });
    }

}