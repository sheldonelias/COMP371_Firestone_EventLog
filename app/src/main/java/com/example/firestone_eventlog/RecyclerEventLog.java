package com.example.firestone_eventlog;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RecyclerEventLog extends AppCompatActivity {

    //Structural variables declared
    RecyclerView recyclerView;
    ArrayList<EventModel> eventList;
    EventAdapter eventAdapter;
    FirebaseFirestore ffDB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_event_log);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        //Recyclers must use the LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ffDB = FirebaseFirestore.getInstance();




        eventList = new ArrayList<>();

        eventAdapter = new EventAdapter(RecyclerEventLog.this, eventList);

        //Recyclers must have an adapter
        //tuples ArrayList object is sent to Adapter to expand with RecyclerView object
        recyclerView.setAdapter(eventAdapter);

        readEventsFromDatabase();
    }



    private void readEventsFromDatabase()
    {
        ffDB.collection("EventLog").orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>()
                {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null)
                        {
                            Log.e("Firestore error", error.getMessage());
                            System.out.println("Firestore error: " + error.getMessage());
                        }

                        for(DocumentChange dc : value.getDocumentChanges())
                        {

                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {
                                //System.out.println("SOMETHING: " + dc.getDocument().toObject(EventModel.class));

                                eventList.add(dc.getDocument().toObject(EventModel.class));
                            }
                        }

                        eventAdapter.notifyDataSetChanged();

                    }
                });
    }
}

