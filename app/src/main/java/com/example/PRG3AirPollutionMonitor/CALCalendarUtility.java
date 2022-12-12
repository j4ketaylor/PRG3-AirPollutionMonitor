package com.example.PRG3AirPollutionMonitor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
                daysInMonthArray.add(null);
            else
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(),i - dayOfWeek));
        }
        return daysInMonthArray;
    }

    public static ArrayList<LocalDate> daysWeekArray(LocalDate selectedDate)
    {
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate currentDate = sundayOfWeek(selectedDate);
        assert currentDate != null;
        LocalDate endDate = currentDate.plusWeeks(1);

        while (currentDate.isBefore(endDate))
        {
            days.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return days;
    }

    private static LocalDate sundayOfWeek(LocalDate currentDate)
    {
        LocalDate oneWeekAgo = currentDate.minusWeeks(1);
        while(currentDate.isAfter(oneWeekAgo))
        {
            if(currentDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                return currentDate;
            currentDate = currentDate.minusDays(1);
        }
        return null;
    }

}
