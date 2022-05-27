package com.example.onlinetaskbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Task5 extends AppCompatActivity {
    //autentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser mUser;

    //database
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    //screen
    private Button saveButton;
    private Button clearButton;

    private Button mainMenuButton;

    private EditText taskNameEditText;
    private EditText taskWeightEditText;
    private EditText infoEditText;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task5);

        //database
        database = FirebaseDatabase.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = database.getReference(userId);

        //screen
        saveButton = findViewById(R.id.saveButton);
        clearButton = findViewById(R.id.clearButton);
        mainMenuButton = findViewById(R.id.mainMenuButton);
        taskNameEditText = findViewById(R.id.taskNameEditText);
        taskWeightEditText = findViewById(R.id.taskWeightEditText);
        infoEditText = findViewById(R.id.infoEditText);


        databaseReference.child("name5").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && !String.valueOf(task.getResult().getValue()).equals("null")){
                    String name5 = String.valueOf(task.getResult().getValue());
                    taskNameEditText.setText(name5);

                } else {
                    Toast.makeText(Task5.this,"Task Name is Empty!", Toast.LENGTH_LONG).show();
                }
            }
        });

        databaseReference.child("weight5").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && !String.valueOf(task.getResult().getValue()).equals("null")){
                    String weight5 = String.valueOf(task.getResult().getValue());
                    taskWeightEditText.setText(weight5);

                } else {
                    Toast.makeText(Task5.this,"Task Weight is Empty!", Toast.LENGTH_LONG).show();
                }
            }
        });

        databaseReference.child("info5").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && !String.valueOf(task.getResult().getValue()).equals("null")){
                    String info5 = String.valueOf(task.getResult().getValue());
                    infoEditText.setText(info5);

                } else {
                    Toast.makeText(Task5.this,"No Additional Informations!", Toast.LENGTH_LONG).show();
                }
            }
        });





        //saving
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference(userId);
                databaseReference.child("name5").setValue(taskNameEditText.getText().toString());
                databaseReference.child("weight5").setValue(taskWeightEditText.getText().toString());
                databaseReference.child("info5").setValue(infoEditText.getText().toString());
                Toast.makeText(Task5.this, "Saved Task!", Toast.LENGTH_SHORT).show();
            }
        });

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

        //mainMenu
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainMenu();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskNameEditText.setText("");
                taskWeightEditText.setText("");
                infoEditText.setText("");
                databaseReference = FirebaseDatabase.getInstance().getReference(userId);
                databaseReference.child("name5").setValue(taskNameEditText.getText().toString());
                databaseReference.child("weight5").setValue(taskWeightEditText.getText().toString());
                databaseReference.child("info5").setValue(infoEditText.getText().toString());
                Toast.makeText(Task5.this, "Cleared Task!", Toast.LENGTH_SHORT).show();
            }
        });



    }
    public void goToMainMenu()
    {
        Intent i = new Intent(this, LoggedOn.class);
        startActivity(i);
    }

    public void logout()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}