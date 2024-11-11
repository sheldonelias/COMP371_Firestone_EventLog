package com.example.firestone_eventlog;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder
{
    TextView countView, dateView, timeView, typeView, descriptionView;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);

        countView = itemView.findViewById(R.id.count_data);
        dateView = itemView.findViewById(R.id.date_data);
        timeView = itemView.findViewById(R.id.time_data);
        typeView = itemView.findViewById(R.id.type_data);
        descriptionView = itemView.findViewById(R.id.description_data);

    }
}


