package com.example.PRG3AirPollutionMonitor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

// Class for the Calendar Event, which contains the date, time, and description of the event.

public class CALEvent
{
    public static ArrayList<CALEvent> eventsList = new ArrayList<>();

    public static ArrayList<CALEvent> eventsDate(LocalDate date)
    {
        ArrayList<CALEvent> CALEvents = new ArrayList<>();
        for(CALEvent CALEvent : eventsList)
        {
            if(CALEvent.getDate().equals(date))
                CALEvents.add(CALEvent);
        }
        return CALEvents;
    }

    private String name;
    private LocalDate date;
    private LocalTime time;

    public CALEvent(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
