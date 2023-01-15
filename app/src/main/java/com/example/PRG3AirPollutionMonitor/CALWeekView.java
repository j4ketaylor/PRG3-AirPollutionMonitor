package com.example.PRG3AirPollutionMonitor;

import static com.example.PRG3AirPollutionMonitor.CALCalendarUtility.daysWeekArray;
import static com.example.PRG3AirPollutionMonitor.CALCalendarUtility.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

// Secondary activity for the Calendar feature. Displays the calendar and the events for the selected date.

public class CALWeekView extends AppCompatActivity implements CALCalendarAdapt.onItemClickListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecView;
    private ListView eventListView;
    CALDBHelper XCALDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_activity_week_view);
        XCALDBHelper = new CALDBHelper(this);
        initWidgets();
        setWeekView();
    }

    private void initWidgets()
    {
        monthYearText = findViewById(R.id.monthYearText);
        calendarRecView = findViewById(R.id.calendarRecyclerView);
        eventListView = findViewById(R.id.eventListView);
    }
    private void setWeekView()
    {
        Cursor DBData = XCALDBHelper.accessData();
        if (CALEvent.eventsList.isEmpty()) {
            while (DBData.moveToNext()) {
                String name = DBData.getString(1);
                LocalDate date = LocalDate.parse(DBData.getString(2));
                LocalTime time = LocalTime.parse(DBData.getString(3));
                CALEvent newCALEvent = new CALEvent(name, date, time);
                CALEvent.eventsList.add(newCALEvent);
            }
        }
        monthYearText.setText(monthYearFromDate(CALCalendarUtility.selectedDate));
        ArrayList<LocalDate> days = daysWeekArray(CALCalendarUtility.selectedDate);
        CALCalendarAdapt CALCalendarAdapt = new CALCalendarAdapt(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecView.setLayoutManager(layoutManager);
        calendarRecView.setAdapter(CALCalendarAdapt);
        setEventAdapter();
    }


    public void previousWeek(View view)
    {
        CALCalendarUtility.selectedDate = CALCalendarUtility.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeek(View view)
    {
        CALCalendarUtility.selectedDate = CALCalendarUtility.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CALCalendarUtility.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter()
    {
        ArrayList<CALEvent> dailyCALEvents = CALEvent.eventsDate(CALCalendarUtility.selectedDate);
        CALEventAdapt CALEventAdapt = new CALEventAdapt(getApplicationContext(), dailyCALEvents);
        eventListView.setAdapter(CALEventAdapt);
    }

    public void newCALEvent(View view)
    {
        startActivity(new Intent(this, CALEditEvent.class));
    }
}