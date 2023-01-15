package com.example.PRG3AirPollutionMonitor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Utility class for the Calendar Activity, contains methods for structuring the array of dates to be displayed.

public class CALCalendarUtility {
    public static LocalDate selectedDate;

    public static String formatDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    public static String formatTime(LocalTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return time.format(formatter);
    }

    public static String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public static ArrayList<LocalDate> daysInMonthArray(LocalDate date)
    {
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = CALCalendarUtility.selectedDate.withDayOfMonth(1);

        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i<=42; i++)
        {
            if(i<dayOfWeek || i>daysInMonth+dayOfWeek-1)
            {
                daysInMonthArray.add(null);
            }
            else
            {
                daysInMonthArray.add(LocalDate.of(CALCalendarUtility.selectedDate.getYear(), CALCalendarUtility.selectedDate.getMonth(), i-dayOfWeek+1));
            }
        }
        return daysInMonthArray;
    }

    public static ArrayList<LocalDate> daysWeekArray(LocalDate selectedDate)
    {
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate currentDate = selectedDate.with(DayOfWeek.MONDAY);
        for(int i = 0; i<7; i++)
        {
            days.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return days;
    }

}
