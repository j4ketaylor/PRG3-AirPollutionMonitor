package com.example.PRG3AirPollutionMonitor;

import static com.example.PRG3AirPollutionMonitor.CALCalendarUtility.daysInMonthArray;
import static com.example.PRG3AirPollutionMonitor.CALCalendarUtility.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

// Primary activity for the Calendar feature. Displays the calendar and digital clock.

public class UsageCalendar extends AppCompatActivity implements CALCalendarAdapt.onItemClickListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_calendar);
        initWidgets();
        CALCalendarUtility.selectedDate = LocalDate.now();
        setMonthView();
    }

    private void initWidgets()
    {
        monthYearText = findViewById(R.id.monthYearText);
        calendarRecView = findViewById(R.id.calendarRecyclerView);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CALCalendarUtility.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CALCalendarUtility.selectedDate);

        CALCalendarAdapt CALCalendarAdapt = new CALCalendarAdapt(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecView.setLayoutManager(layoutManager);
        calendarRecView.setAdapter(CALCalendarAdapt);
    }

    public void previousMonth(View view)
    {
        CALCalendarUtility.selectedDate = CALCalendarUtility.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonth(View view)
    {
        CALCalendarUtility.selectedDate = CALCalendarUtility.selectedDate.plusMonths(1);
        setMonthView();
    }

    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CALCalendarUtility.selectedDate = date;
            setMonthView();
            Intent intent = new Intent(this, CALWeekView.class);
            startActivity(intent);
        }
    }

    public void goToWeekView(View view)
    {
        Intent intent = new Intent(this, CALWeekView.class);
        startActivity(intent);
    }
}