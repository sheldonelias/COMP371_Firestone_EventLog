package com.example.firestone_eventlog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //View variables declared
    EditText eventDate;
    EditText eventTime;
    EditText eventType;
    EditText eventDescription;
    Button recordButton;


    ProgressDialog progressDialog;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();*/


        eventDate = findViewById(R.id.date_edit_text);
        eventTime = findViewById(R.id.time_edit_text);
        eventType = findViewById(R.id.event_type_edit_text);
        eventDescription = findViewById(R.id.event_description_edit_text);

        recordButton = findViewById(R.id.record_button);



        recordButton.setOnClickListener(

                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentToList = new Intent(MainActivity.this, RecyclerEventLog.class);
                startActivity(intentToList);

                //Transfer editText data to database


            }
        });





    }



}