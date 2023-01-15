package com.example.PRG3AirPollutionMonitor;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

// View for the Calendar RecyclerView, which displays the dates in the month.

public class CALCalendarView extends RecyclerView.ViewHolder  implements View.OnClickListener
{
    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView dayOfMonth;
    private final CALCalendarAdapt.onItemClickListener listener;
    public CALCalendarView(@NonNull View itemView, CALCalendarAdapt.onItemClickListener listener, ArrayList<LocalDate> days)
    {
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.listener = listener;
        this.days = days;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
