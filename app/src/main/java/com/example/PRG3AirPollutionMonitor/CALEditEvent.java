package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

// Activity for creating and editing usage records.

public class CALEditEvent extends AppCompatActivity {

    private EditText eventName;
    private TextView eventDate, eventTime;
    private LocalTime time;
    CALDBHelper XCALDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_activity_edit_event);
        initWidgets();
        time = LocalTime.now();
        eventDate.setText("Date: " + CALCalendarUtility.formatDate(CALCalendarUtility.selectedDate));
        eventTime.setText("Time: " + CALCalendarUtility.formatTime(time));
        XCALDBHelper = new CALDBHelper(this);
    }

    private void initWidgets()
    {
        eventName = findViewById(R.id.eventName);
        eventDate = findViewById(R.id.eventDate);
        eventTime = findViewById(R.id.eventTime);
    }

    public void saveEvent(View view)
    {
        String name = eventName.getText().toString();
        CALEvent newCALEvent = new CALEvent(name, CALCalendarUtility.selectedDate, time);
        CALEvent.eventsList.add(newCALEvent);
        boolean DataInserted = XCALDBHelper.insertData(name, CALCalendarUtility.selectedDate, time);
        if(DataInserted)
            finish();
    }
}