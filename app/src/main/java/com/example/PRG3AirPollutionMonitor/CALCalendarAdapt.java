package com.example.PRG3AirPollutionMonitor;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CALCalendarAdapt extends RecyclerView.Adapter<CALCalendarView>
{
    private final ArrayList<LocalDate> days;
    private final calOnItemClickListener listener;

    public CALCalendarAdapt(ArrayList<LocalDate> days, calOnItemClickListener listener)
    {
        this.days = days;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CALCalendarView onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cal_calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if(days.size()>15)
            layoutParams.height = (int) (parent.getHeight() * 0.167);
        else
            layoutParams.height = parent.getHeight();

        return new CALCalendarView(view, listener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CALCalendarView holder, int position)
    {
        final LocalDate date = days.get(position);
        if(date == null)
            holder.dayOfMonth.setText("");
        else
        {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CALCalendarUtility.selectedDate))
                holder.parentView.setBackgroundColor(Color.LTGRAY);

        }
    }

    @Override
    public int getItemCount()
    {
        return days.size();
    }

    public interface calOnItemClickListener
    {
        void calOnItemClick(int position, LocalDate date);
    }
}
