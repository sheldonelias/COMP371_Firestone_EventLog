package com.example.firestone_eventlog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder>
{
    Context context;
    ArrayList<EventModel> eventList;

    public EventAdapter(Context context, ArrayList<EventModel> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.event_card, parent, false);

        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position)
    {
        EventModel event = eventList.get(position);


        holder.countView.setText(event.eventCount);
        holder.dateView.setText(event.date);
        holder.timeView.setText(event.time);
        holder.typeView.setText(event.type);
        holder.descriptionView.setText(event.description);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}
