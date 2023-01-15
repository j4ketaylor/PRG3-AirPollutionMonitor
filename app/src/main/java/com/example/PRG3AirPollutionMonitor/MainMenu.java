package com.example.PRG3AirPollutionMonitor;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.time.Period;
import java.time.format.DateTimeFormatter;

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
    Button countdown_button;

    //define variables needed for the countdown button
    public static long start_time_ms;
    private static long time_left_ms;
    private static long end_time;
    private static boolean timer_running = false;
    private String eventName;
    private LocalTime time;
    private LocalDate date;
    String prescription_expiry_date;
    int prescription_uses;

    private static Context context;

    CALDBHelper XCALDBHelper;
    LocalDate expiryDate;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XCALDBHelper = new CALDBHelper(this);

        // by ID we can use each component which id is assign in xml file
        // use findViewById() to get the Button
        airQualityButton = (Button) findViewById(R.id.air_quality_button);
        emergencyButton = (Button) findViewById(R.id.emergency_button);
        inhalerButton = (Button) findViewById(R.id.inhaler_button);
        countdown_button = (Button) findViewById(R.id.countdown_button);
        welcomeTitle = (TextView) findViewById(R.id.welcome_title);

        // In question1 get the TextView use by findViewById()
        // In TextView set question Answer for message

        // Add_button add clicklistener
        airQualityButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, AirQuality.class);
            startActivity(intent);
        });

        emergencyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Emergency.class);
            startActivity(intent);
        });

        inhalerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, InhalerMenu.class);
            startActivity(intent);
        });
        context = this;
        countdown_button.setOnClickListener(v -> {

            load_prescription_dosage_interval();
            time = LocalTime.now();
            date = LocalDate.now();

            load_prescription_detail();
            update_prescription_use();
            prescription_use_notification();
            createNotificationChannel();
            if (timer_running){
                Intent intent = new Intent(MainMenu.this,OverdosePopup.class);
                startActivity(intent);
                eventName = "Warning! Inhaler Overdosed";
                CALEvent newCALEvent = new CALEvent(eventName, date, time);
                CALEvent.eventsList.add(newCALEvent);
                boolean DataInserted = XCALDBHelper.insertData(eventName, date, time);
            }
            else{
                time_left_ms = start_time_ms;
                startTimer();
                eventName = "Inhaler Use Recorded";
                CALEvent newCALEvent = new CALEvent(eventName, date, time);
                CALEvent.eventsList.add(newCALEvent);
                boolean DataInserted = XCALDBHelper.insertData(eventName, date, time);
            }
        });
        Intent intent = new Intent(MainMenu.this, PollutionPopup.class);
        startActivity(intent);
        load_prescription_detail();
        prescription_use_notification();
        prescription_expiry_notification();
        createNotificationChannel();
    }
    /*Reference 1 - parts of code taken from https://gist.github.com/codinginflow/61e9cec706e7fe94b0ca3fffc0253bf2 */
    //starts the timer
    private void startTimer(){
        end_time = System.currentTimeMillis() + time_left_ms;
        CountDownTimer timer = new CountDownTimer(time_left_ms, 1000) {
            @Override
            public void onTick(long l) {
                time_left_ms = l;
                updatetext();
            }

            @Override
            public void onFinish() {
                resetTimer();
            }
        }.start();
        timer_running = true;
    }
    //resets the timer
    private void resetTimer(){
        timer_running = false;
        countdown_button.setText("log inhaler use");
        time_left_ms = start_time_ms;

    }
    //updates text on timer according to the time left
    private void updatetext(){
        int minutes = (int) time_left_ms/1000/60;
        int seconds = (int) time_left_ms/1000%60;
        String time_left_formatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        countdown_button.setText(time_left_formatted);
    }
    //saves information of timer
    public static void saveVar(Context context){
        SharedPreferences pref = context.getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("end_time",end_time);
        editor.putLong("time_left_ms",time_left_ms);
        editor.putBoolean("running",timer_running);
        editor.apply();
    }
    @Override
    //saves information of the timer when the app is stopped
    protected void onStop(){
        super.onStop();
        saveVar(this);

    }
    @Override
    //determines action of timer based on information saved when the app was stopped
    protected void onStart(){
        super.onStart();

        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);

        timer_running = pref.getBoolean("running",false);

        load_prescription_dosage_interval();

        if(timer_running){
            end_time = pref.getLong("end_time",0);
            time_left_ms = end_time - System.currentTimeMillis();
            if(time_left_ms<0){
                resetTimer();
            }
            else{
                startTimer();
            }
        }
    }
    /* end of reference 1 */

    //loads the prescription dosage interval from the database
    public void load_prescription_dosage_interval(){
        try{
            SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs",MODE_PRIVATE);
            double dosage_interval = Double.parseDouble(sharedPreferences.getString("text3", ""));
            start_time_ms = (long) (dosage_interval * 60000);

        } catch(Exception e) {
            Log.e( "Failed to load dosage interval", e.getMessage());
            start_time_ms = 6000;
        }
    }

    //loads prescription details from database
    public void load_prescription_detail(){
        try{
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        this.prescription_expiry_date = sharedPreferences.getString("text2", "");
        this.prescription_uses = Integer.parseInt(sharedPreferences.getString("text", ""));}
        catch (Exception e){
            Log.e( "Failed to load inhaler prescription", e.getMessage());
        }
    }

    //updates prescription uses
    public void update_prescription_use(){
        try{
            SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (prescription_uses > 0) {
                prescription_uses--;
            }

            else {
                prescription_uses = 0;
            }
                editor.putString("text", String.valueOf(prescription_uses));
                editor.apply();

        }
        catch (Exception e){
            Log.e( "Failed to update inhaler prescription", e.getMessage());
        }
    }

    //Check if inhaler is used up and notify user
    public void prescription_use_notification(){
        try {
            Toast.makeText(MainMenu.this, "Remaining inhaler uses: " + prescription_uses, Toast.LENGTH_SHORT).show();
            if (prescription_uses <= 10 && prescription_uses > 0) {
                inhaler_use_notify();
            } else if (prescription_uses <= 0) {
                inhaler_use_notify_urgent();

            }
        } catch (Exception e) {
            Log.e( "Failed to process inhaler uses", e.getMessage());
        }
    }

    //Check if inhaler is expired and notify user
    public void prescription_expiry_notification(){
            try {
            expiryDate = LocalDate.parse(prescription_expiry_date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Toast.makeText(MainMenu.this, "Inhaler Expiry Date: " + expiryDate, Toast.LENGTH_SHORT).show();
            if (expiryDate.isBefore(LocalDate.now().plus(Period.ofWeeks(1)))&& expiryDate.isAfter(LocalDate.now())) {
                inhaler_expiry_notify();
            } else if (expiryDate.isBefore(LocalDate.now())) {
                inhaler_expiry_notify_urgent();
            }
        } catch (Exception e) {
            Log.e( "Failed to process inhaler expiry date", e.getMessage());
        }
    }

    //Build and customise the individual notifications
    public void inhaler_use_notify(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainMenu.this,"Prescription Notification");
        builder.setSmallIcon(R.drawable.ic_android_black_24dp);
        builder.setContentTitle("Inhaler is low!");
        builder.setContentText("Please prepare for replacement");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainMenu.this);
        managerCompat.notify(1,builder.build());
        
    }

    public void inhaler_use_notify_urgent(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainMenu.this,"Prescription Notification");
        builder.setSmallIcon(R.drawable.ic_android_black_24dp);
        builder.setContentTitle("Inhaler is used up!");
        builder.setContentText("Please make sure you have a replacement!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainMenu.this);
        managerCompat.notify(2,builder.build());

    }

    public void inhaler_expiry_notify(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainMenu.this,"Prescription Notification");
        builder.setSmallIcon(R.drawable.ic_android_black_24dp);
        builder.setContentTitle("Inhaler is about to expire!");
        builder.setContentText("Please prepare for replacement");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainMenu.this);
        managerCompat.notify(3,builder.build());

    }

    public void inhaler_expiry_notify_urgent(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainMenu.this,"Prescription Notification");
        builder.setSmallIcon(R.drawable.ic_android_black_24dp);
        builder.setContentTitle("Inhaler is expired!");
        builder.setContentText("Please make sure you have a replacement!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainMenu.this);
        managerCompat.notify(4,builder.build());

    }

    //Constructs Notification channel where individual notifications are assigned

    public void createNotificationChannel(){
        NotificationChannel prescrip_channel = new NotificationChannel("Prescription Notification","Prescription Notification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(prescrip_channel);
    }
}