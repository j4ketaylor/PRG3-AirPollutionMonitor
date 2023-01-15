package com.example.PRG3AirPollutionMonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

// Adapter for the Event RecyclerView, which displays the events in the selected date.

public class CALEventAdapt extends ArrayAdapter<CALEvent>
{
    public CALEventAdapt(@NonNull Context context, List<CALEvent> CALEvents)
    {
        super(context, 0, CALEvents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        CALEvent CALEvent = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cal_event_cell, parent, false);

        TextView eventCell = convertView.findViewById(R.id.calEventCell);

        String eventTitle = CALEvent.getName() + " " + CALCalendarUtility.formatTime(CALEvent.getTime());
        eventCell.setText(eventTitle);
        return convertView;

    }

}
