package com.example.PRG3AirPollutionMonitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;


public class MainMenu extends AppCompatActivity {

    // define the global variable
    TextView question1;
    // Add button Move to Activity
    Button next_Activity_button;
    Button emergency_button;
    Button inhaler_button;

    private static final long start_time_ms = 3600000;
    private Button countdown_button;
    private CountDownTimer timer;
    private long time_left_ms;
    private long end_time;
    private boolean timer_running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countdown_button = findViewById(R.id.countdown_timer);
        countdown_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer_running){
                    startActivity(new Intent(MainMenu.this,overdose_popup.class));
                }
                else{
                    startTimer();
                }
            }
        });

        // by ID we can use each component which id is assign in xml file
        // use findViewById() to get the Button
        next_Activity_button = (Button) findViewById(R.id.first_activity_button);
        emergency_button = (Button) findViewById(R.id.emergency);
        inhaler_button = (Button) findViewById(R.id.inhaler_menu);
        question1 = (TextView) findViewById(R.id.question1_id);

        // In question1 get the TextView use by findViewById()
        // In TextView set question Answer for message
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


    }

    private void startTimer(){
        end_time = System.currentTimeMillis() + time_left_ms;
        timer = new CountDownTimer(time_left_ms, 1000) {
            @Override
            public void onTick(long l) {
                time_left_ms = l;
                updatetext();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                countdown_button.setText("log inhaler use");
                time_left_ms = start_time_ms;
            }
        }.start();
        timer_running = true;
    }
    private void updatetext(){
        int minutes = (int) time_left_ms/1000/60;
        int seconds = (int) time_left_ms/1000%60;
        String time_left_formatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        countdown_button.setText(time_left_formatted);
    }
    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("time_left",time_left_ms);
        editor.putLong("end_time",end_time);
        editor.putBoolean("running",timer_running);
        editor.apply();
    }
    @Override
    protected void onStart(){
        super.onStart();
        SharedPreferences pref = getSharedPreferences("pref",MODE_PRIVATE);
        time_left_ms = pref.getLong("time_left",start_time_ms);
        timer_running = pref.getBoolean("running",false);
        if(timer_running){
            end_time = pref.getLong("end_time",0);
            time_left_ms = end_time - System.currentTimeMillis();
            if(time_left_ms<0){
                time_left_ms = start_time_ms;
                timer_running = false;
                countdown_button.setText("log inhaler use");
            }else{
                startTimer();
            }
        }
    }
}