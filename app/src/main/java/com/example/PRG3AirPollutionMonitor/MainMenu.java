package com.example.PRG3AirPollutionMonitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class MainMenu extends AppCompatActivity {

    // define the global variable
    TextView question1;
    // Add button Move to Activity
    Button next_Activity_button;
    Button emergency_button;
    Button inhaler_button;
    Button countdown_button;

    //define variables needed for the countdown button
    private static final long start_time_ms = 60000;
    private long time_left_ms;
    private long end_time;
    private boolean timer_running = false;
    private String eventName;
    private LocalTime time;
    private LocalDate date;
    public static int inhaler_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // by ID we can use each component which id is assign in xml file
        // use findViewById() to get the Button
        next_Activity_button = (Button) findViewById(R.id.first_activity_button);
        emergency_button = (Button) findViewById(R.id.emergency);
        inhaler_button = (Button) findViewById(R.id.inhaler_menu);
        countdown_button = (Button) findViewById(R.id.countdown_timer);
        question1 = (TextView) findViewById(R.id.question1_id);

        // In question1 get the TextView use by findViewById()
        // In TextView set question Answer for message

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        question1.setTypeface(customFont);
        question1.setText("Welcome\n");

        // Add_button add clicklistener
        next_Activity_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainMenu.this, AirQuality.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        emergency_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainMenu.this, Emergency.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        inhaler_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainMenu.this, InhalerMenu.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        countdown_button.setOnClickListener(v -> {
            time = LocalTime.now();
            date = LocalDate.now();
            inhaler_count++;
            //if timer is already running, show a popup indicating overdose and log overdose on calendar
            if (timer_running){
                Intent intent = new Intent(MainMenu.this,OverdosePopup.class);
                startActivity(intent);
                eventName = "Warning! Inhaler Overdosed";
                CALEvent newCALEvent = new CALEvent(eventName, date, time);
                CALEvent.eventsList.add(newCALEvent);
            }
            //starts the timer if it is not currently running, and logs the usage on calendar
            else{
                startTimer();
                eventName = "Inhaler Use Recorded";
                CALEvent newCALEvent = new CALEvent(eventName, date, time);
                CALEvent.eventsList.add(newCALEvent);

            }
        });
    }
    //starts the timer
    private void startTimer(){
        //finds end time based on time left and current time of system, used to be saved to find time left after stopping the app
        end_time = System.currentTimeMillis() + time_left_ms;
        //initializes the timer
        CountDownTimer timer = new CountDownTimer(time_left_ms, 1000) {
            @Override
            //updates text of timer per tick
            public void onTick(long l) {
                time_left_ms = l;
                updatetext();
            }

            @Override
            //resets timer when it ends
            public void onFinish() {
                resetTimer();
            }
            //starts the countdown
        }.start();
        //shows app that the timer is currently running
        timer_running = true;
    }
    //resets the timer
    private void resetTimer(){
        //shows app that the timer is not running
        timer_running = false;
        //resets text on timer
        countdown_button.setText("log inhaler use");
        //resets time of timer
        time_left_ms = start_time_ms;
    }
    //updates text on timer according to the time left
    private void updatetext(){
        //calculates time left in minutes and seconds
        int minutes = (int) time_left_ms/1000/60;
        int seconds = (int) time_left_ms/1000%60;
        //formats time for display
        String time_left_formatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        //sets text on timer to time left
        countdown_button.setText(time_left_formatted);
    }
    @Override
    //saves information of the timer when the app is stopped
    protected void onStop(){
        super.onStop();
        //adds variables to a shared preference
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("end_time",end_time);
        editor.putLong("time_left_ms",time_left_ms);
        editor.putBoolean("running",timer_running);
        editor.apply();
    }
    @Override
    //determines action of timer based on information saved when the app was stopped
    protected void onStart(){
        super.onStart();
        //loads information of the timer before it was stopped when the app is started
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        //defaults timer as not running
        timer_running = pref.getBoolean("running",false);
        //default time left is the start time
        time_left_ms = pref.getLong("time_left_ms", start_time_ms);
        //if the timer was running when the app was stopped, loads the end time and finds the current time left
        if(timer_running){
            end_time = pref.getLong("end_time",0);
            //finds time left by subtracting current time of the system from saved end time
            time_left_ms = end_time - System.currentTimeMillis();
            //resets the timer if the app was stopped for longer than the time left before being stopped
            if(time_left_ms<0){
                resetTimer();
            }
            //starts the timer again otherwise
            else{
                startTimer();
            }
        }
    }


}