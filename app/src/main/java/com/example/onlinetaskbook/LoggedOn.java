package com.example.onlinetaskbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoggedOn extends AppCompatActivity {

    //autentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;

    //database
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    //screen
    private Button logoutButton;

    private Button showButton1;
    private Button showButton2;
    private Button showButton3;
    private Button showButton4;
    private Button showButton5;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //database
        database = FirebaseDatabase.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //screen
        logoutButton = findViewById(R.id.logoutButton);

        showButton1 = findViewById(R.id.showButton1);
        showButton2 = findViewById(R.id.showButton2);
        showButton3 = findViewById(R.id.showButton3);
        showButton4 = findViewById(R.id.showButton4);
        showButton5 = findViewById(R.id.showButton5);

        //autentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if(mUser==null)
                {
                    logout();
                }
            }
        };

        //lougout
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            }
        });

        //pokazywanie
        showButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show1();
            }
        });
        showButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2();
            }
        });
        showButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show3();
            }
        });
        showButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show4();
            }
        });
        showButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show5();
            }
        });

    }
    //
    public void logout()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void show1() {
        Intent i = new Intent(this, Task1.class);
        startActivity(i);
    }
    public void show2() {
        Intent i = new Intent(this, Task2.class);
        startActivity(i);
    }
    public void show3() {
        Intent i = new Intent(this, Task3.class);
        startActivity(i);
    }
    public void show4() {
        Intent i = new Intent(this, Task4.class);
        startActivity(i);
    }
    public void show5() {
        Intent i = new Intent(this, Task5.class);
        startActivity(i);
    }
}