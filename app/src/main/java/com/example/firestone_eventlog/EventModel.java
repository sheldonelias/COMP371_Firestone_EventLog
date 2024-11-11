package com.example.firestone_eventlog;

public class EventModel
{


    //Instance Variables
    String eventCount;
    String date;
    String time;
    String type;
    String description;


    EventModel() {}

    EventModel(String eventCount, String date, String time, String type, String description)
    {
        this.eventCount = eventCount;
        this.date = date;
        this.time = time;
        this.type = type;
        this.description = description;
    }


    public String getEventCount() {
        return eventCount;
    }

    public void setEventCount(String eventCount) {
        this.eventCount = eventCount;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
