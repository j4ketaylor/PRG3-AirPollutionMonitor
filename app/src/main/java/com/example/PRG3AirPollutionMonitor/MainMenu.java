package com.example.PRG3AirPollutionMonitor;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class MainMenu extends AppCompatActivity {

    // define the global variable
    TextView welcomeTitle;
    // Add button Move to Activity
    Button airQualityButton;
    Button emergencyButton;
    Button inhalerButton;
    Button countdownButton;

    //define variables needed for the countdown button

    public static long start_time_ms;
    private static long time_left_ms;
    private static long end_time;
    private static boolean timer_running = false;
    private String eventName;
    private LocalTime time;
    private LocalDate date;
    public static int inhaler_count = 0;

    private static Context context;

    CALDBHelper XCALDBHelper;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





//        try {
//            System.out.println(PrescriptionDetails.text3);
//            start_time_ms = Long.valueOf(PrescriptionDetails.text3)*60000;
//        } catch (NumberFormatException e) {
//            System.out.println("This happened");
//            start_time_ms = 6000;
//        }

        XCALDBHelper = new CALDBHelper(this);

        // by ID we can use each component which id is assign in xml file
        // use findViewById() to get the Button
        airQualityButton = (Button) findViewById(R.id.air_quality_button);
        emergencyButton = (Button) findViewById(R.id.emergency_button);
        inhalerButton = (Button) findViewById(R.id.inhaler_button);
        countdownButton = (Button) findViewById(R.id.countdown_button);
        welcomeTitle = (TextView) findViewById(R.id.welcome_title);

        // In question1 get the TextView use by findViewById()
        // In TextView set question Answer for message

        // Add_button add clicklistener
        airQualityButton.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainMenu.this, AirQuality.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        emergencyButton.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainMenu.this, Emergency.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        inhalerButton.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainMenu.this, InhalerMenu.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
        context = this;
        countdownButton.setOnClickListener(v -> {
//            try {
//                System.out.println(PrescriptionDetails.text3);
//                start_time_ms = Long.valueOf(PrescriptionDetails.text3)*60000;
//            } catch (NumberFormatException e) {
//                System.out.println("This happened");
//                start_time_ms = 6000;
//            }
            time = LocalTime.now();
            date = LocalDate.now();
            prescription_notification();
            inhaler_count++;
            //if timer is already running, show a popup indicating overdose and log overdose on calendar
            if (timer_running){
                Intent intent = new Intent(MainMenu.this,OverdosePopup.class);
                startActivity(intent);
                eventName = "Warning! Inhaler Overdosed";
                CALEvent newCALEvent = new CALEvent(eventName, date, time);
                CALEvent.eventsList.add(newCALEvent);
                boolean DataInserted = XCALDBHelper.insertData(eventName, date, time);
            }
            //starts the timer if it is not currently running, and logs the usage on calendar
            else{
                time_left_ms = start_time_ms;
                startTimer();
                eventName = "Inhaler Use Recorded";
                CALEvent newCALEvent = new CALEvent(eventName, date, time);
                CALEvent.eventsList.add(newCALEvent);
                boolean DataInserted = XCALDBHelper.insertData(eventName, date, time);

            }
        });

        createNotificationChannel();




    }
    //starts the timer
    private void startTimer(){
//        try {
//            System.out.println(PrescriptionDetails.text3);
//            start_time_ms = Long.valueOf(PrescriptionDetails.text3)*60000;
//        } catch (NumberFormatException e) {
//            System.out.println("This happened");
//            start_time_ms = 6000;
//        }
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
//        try {
//            System.out.println(PrescriptionDetails.text3);
//            start_time_ms = Long.valueOf(PrescriptionDetails.text3)*60000;
//        } catch (NumberFormatException e) {
//            System.out.println("This happened");
//            start_time_ms = 6000;
//        }
        //shows app that the timer is not running
        timer_running = false;
        //resets text on timer
        countdownButton.setText("log inhaler use");
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
        countdownButton.setText(time_left_formatted);
    }
    public static void saveVar(Context context){
        SharedPreferences pref = context.getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("end_time",end_time);
        editor.putLong("time_left_ms",time_left_ms);
        editor.putBoolean("running",timer_running);
        try{
            editor.putLong("dosage_interval",Long.valueOf(PrescriptionDetails.text3)*60000);
        } catch(NumberFormatException e){}
        editor.apply();
    }
    @Override
    //saves information of the timer when the app is stopped
    protected void onStop(){
        super.onStop();
        //adds variables to a shared preference
        saveVar(this);
    }
    @Override
    //determines action of timer based on information saved when the app was stopped
    protected void onStart(){
        super.onStart();
//        try {
//            System.out.println(PrescriptionDetails.text3);
//            start_time_ms = Long.valueOf(PrescriptionDetails.text3)*60000;
//        } catch (NumberFormatException e) {
//            System.out.println("This happened");
//            start_time_ms = 6000;
//        }
        //loads information of the timer before it was stopped when the app is started
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        //defaults timer as not running
        timer_running = pref.getBoolean("running",false);
        //default time left is the start time
            time_left_ms = pref.getLong("time_left_ms",0);
            try{
                start_time_ms = Long.valueOf(PrescriptionDetails.text3)*60000;
            } catch(NumberFormatException e) {
                start_time_ms = pref.getLong("dosage_interval", 6000);
            }
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

    public void prescription_notification(){
        PrescriptionDetails prescri_details = new PrescriptionDetails();
        int inhaler_uses = prescri_details.getInhalerUsesnot();
        if (inhaler_uses == 0){
            prescription_notify();
        }
    }

    public void prescription_notify(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainMenu.this,"Prescription Notification");
        builder.setSmallIcon(R.drawable.ic_android_black_24dp);
        builder.setContentTitle("Inhaler is low!");
        builder.setContentText("Please make sure you have a replacement!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainMenu.this);
        managerCompat.notify(1,builder.build());

    }
    public void createNotificationChannel(){
        NotificationChannel prescrip_channel = new NotificationChannel("Prescription Notification","Prescription Notification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(prescrip_channel);
    }
}