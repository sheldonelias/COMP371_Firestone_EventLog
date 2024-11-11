package com.example.firestone_eventlog;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //View variables declared
    String eventNumber;
    EditText eventDate;
    EditText eventTime;
    EditText eventType;
    EditText eventDescription;
    Button recordButton;


    FirebaseFirestore ffDB;

    Query query;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ffDB = FirebaseFirestore.getInstance();

        query = ffDB.collection("EventLog");

        AggregateQuery countQuery = query.count();

        countQuery.get(AggregateSource.SERVER).addOnCompleteListener(new OnCompleteListener<AggregateQuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<AggregateQuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Count fetched successfully
                    AggregateQuerySnapshot snapshot = task.getResult();
                    System.out.println("Count: " + snapshot.getCount());

                    eventNumber = Long.toString(snapshot.getCount() + 1);

                } else {
                    System.out.println("Count failed: " + task.getException());
                }
            }
        });

        eventDate = findViewById(R.id.date_edit_text);
        eventTime = findViewById(R.id.time_edit_text);
        eventType = findViewById(R.id.event_type_edit_text);
        eventDescription = findViewById(R.id.event_description_edit_text);

        recordButton = findViewById(R.id.record_button);



        recordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intentToList = new Intent(MainActivity.this, RecyclerEventLog.class);
                startActivity(intentToList);

                //Transfer editText data to database

                recordNewDocumentToFirebase();

            }
        });
    }


    public void recordNewDocumentToFirebase()
    {
        System.out.println("recordNewDocumentToFirebase() activated.");

        //Capturing String values from TextField
        Map<String, Object> event = new HashMap<>();
        event.put("date", eventDate.getText().toString());
        event.put("description", eventDescription.getText().toString());
        event.put("number", eventNumber);
        event.put("time", eventTime.getText().toString());
        event.put("type", eventType.getText().toString());

        //System.out.println("EVENTMAP" + eventMap);

        ffDB.collection("EventLog")
                .add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }


}


/*

Query query = db.collection("cities");
AggregateQuery countQuery = query.count();
countQuery.get(AggregateSource.SERVER).addOnCompleteListener(new OnCompleteListener<AggregateQuerySnapshot>() {
    @Override
    public void onComplete(@NonNull Task<AggregateQuerySnapshot> task) {
        if (task.isSuccessful()) {
            // Count fetched successfully
            AggregateQuerySnapshot snapshot = task.getResult();
            Log.d(TAG, "Count: " + snapshot.getCount());
        } else {
            Log.d(TAG, "Count failed: ", task.getException());
        }
    }
});

 */