package com.example.PRG3AirPollutionMonitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class PrescriptionDetails extends AppCompatActivity {
    private TextView remaining_inhaler_uses_text;
    private TextView remaining_inhaler_uses_display;
    private TextView prescription_expiry_date_text;
    private TextView prescription_expiry_date_display;
    private TextView new_information_text;
    private EditText new_prescription_number_of_uses;
    private EditText new_prescription_expiry_date;

    private Button apply_text_button;
    private Button save_button;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String TEXT2 = "text2";


    private String text;
    private String text2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_details);

        remaining_inhaler_uses_text = (TextView) findViewById(R.id.remaining_inhaler_uses);
        remaining_inhaler_uses_display = (TextView) findViewById(R.id.remaining_inhaler_uses_display_text);
        prescription_expiry_date_text = (TextView) findViewById(R.id.prescription_expiration_date);
        prescription_expiry_date_display = (TextView) findViewById(R.id.prescription_expiration_date_display_text);
        new_prescription_number_of_uses = (EditText) findViewById(R.id.new_prescription_number_of_uses_input);
        new_prescription_expiry_date = (EditText) findViewById(R.id.new_prescription_expiry_date_input);
        new_information_text = (TextView) findViewById(R.id.new_information);
        apply_text_button = (Button) findViewById(R.id.apply_text_button);
        save_button = (Button) findViewById(R.id.save_text_button);

        prescription_expiry_date_display.setText("Expiry Date: \n");
        remaining_inhaler_uses_display.setText("Number of uses: \n");
        new_information_text.setText("Enter new information:\n");

        apply_text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remaining_inhaler_uses_text.setText(new_prescription_number_of_uses.getText().toString());
                prescription_expiry_date_text.setText(new_prescription_expiry_date.getText().toString());
            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        loadData();
        updateViews();

    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, remaining_inhaler_uses_text.getText().toString());
        editor.putString(TEXT2, prescription_expiry_date_text.getText().toString());
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        text2 = sharedPreferences.getString(TEXT2, "");
    }

    public void updateViews() {
        try {
            Integer userUses = Integer.valueOf(text);
            Integer inhalerUses = userUses - MainMenu.inhaler_count;
            remaining_inhaler_uses_text.setText(inhalerUses.toString());
            prescription_expiry_date_text.setText(text2);
        } catch(NumberFormatException e) {
            System.out.println("Error");
        }
    }
}
