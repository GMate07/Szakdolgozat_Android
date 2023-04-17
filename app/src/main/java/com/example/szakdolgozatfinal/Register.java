package com.example.szakdolgozatfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://szakdolgozatfinal-default-rtdb.europe-west1.firebasedatabase.app");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText fullname = findViewById(R.id.fullname);
        final EditText felhasznev = findViewById(R.id.felhasznev);
        final EditText szaktudas = findViewById(R.id.szaktudas);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.conPassword);

        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView loginNowBtn = findViewById(R.id.loginNow);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fullnameTxt = fullname.getText().toString();
                final String felhasznevTxt = felhasznev.getText().toString();
                final String szaktudasTxt = szaktudas.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();

                if (fullnameTxt.isEmpty() || felhasznevTxt.isEmpty() || szaktudasTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this, "Minden mezőt töltsön ki!", Toast.LENGTH_SHORT).show();
                }

                else if (!passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(Register.this, "A jelszavak nem egyeznek!", Toast.LENGTH_SHORT).show();
                }

                else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(felhasznevTxt)){
                                Toast.makeText(Register.this, "Ezzel a telefonszámmal már regisztráltak", Toast.LENGTH_SHORT).show();
                            }

                            else {

                                databaseReference.child("users").child(felhasznevTxt).child("fullname").setValue(fullnameTxt);
                                databaseReference.child("users").child(felhasznevTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(felhasznevTxt).child("szaktudas").setValue(szaktudasTxt);
                                databaseReference.child("users").child(felhasznevTxt).child("password").setValue(passwordTxt);

                                Toast.makeText(Register.this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });

        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}