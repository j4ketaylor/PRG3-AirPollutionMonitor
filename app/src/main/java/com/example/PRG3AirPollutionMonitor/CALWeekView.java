package com.example.PRG3AirPollutionMonitor;

import static com.example.PRG3AirPollutionMonitor.CALCalendarUtility.daysWeekArray;
import static com.example.PRG3AirPollutionMonitor.CALCalendarUtility.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CALWeekView extends AppCompatActivity implements CALCalendarAdapt.calOnItemClickListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_activity_week_view);
        initWidgets();
        CALCalendarUtility.selectedDate = LocalDate.now();
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
        monthYearText.setText(monthYearFromDate(CALCalendarUtility.selectedDate));
        ArrayList<LocalDate> days = daysWeekArray(CALCalendarUtility.selectedDate);
        CALCalendarAdapt CALCalendarAdapt = new CALCalendarAdapt(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecView.setLayoutManager(layoutManager);
        calendarRecView.setAdapter(CALCalendarAdapt);
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
    public void calOnItemClick(int position, LocalDate date)
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