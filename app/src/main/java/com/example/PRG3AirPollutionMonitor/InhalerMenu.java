package com.example.PRG3AirPollutionMonitor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InhalerMenu extends AppCompatActivity {

    Button check_calendar_button;
    Button check_prescription_details_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inhaler_menu);

        check_calendar_button = (Button) findViewById(R.id.check_calendar);
        check_prescription_details_button = (Button) findViewById(R.id.check_prescription_details);


        check_calendar_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(InhalerMenu.this, UsageCalendar.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });

        check_prescription_details_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. Your code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(InhalerMenu.this, PrescriptionDetails.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
    }





}