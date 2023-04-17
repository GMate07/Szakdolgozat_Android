package com.example.szakdolgozatfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://szakdolgozatfinal-default-rtdb.europe-west1.firebasedatabase.app");

    Button logout;
    TextView usernameTV;
    TextView nameTV;
    TextView professionTV;
    TextView emailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        logout = findViewById(R.id.logout);
        usernameTV = findViewById(R.id.usernameTV);
        nameTV = findViewById(R.id.nameTV);
        professionTV = findViewById(R.id.professionTV);
        emailTV = findViewById(R.id.emailTV);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usernameTV.setText("Felhasználónév: " + Login.CURRENT_USERNAME);
                nameTV.setText(snapshot.child(Login.CURRENT_USERNAME).child("fullname").getValue(String.class));
                professionTV.setText(snapshot.child(Login.CURRENT_USERNAME).child("szaktudas").getValue(String.class));
                emailTV.setText(snapshot.child(Login.CURRENT_USERNAME).child("email").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile.this, "Megnyitottad az adatbázist!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile.this, Database.class));
                finish();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile.this, "Megnyitottad a Teachable Machinet!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile.this, TeachableMachine.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile.this, "Megnyitottad a profilodat!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile.this, Profile.class));
                finish();
            }
        });




    }

}
