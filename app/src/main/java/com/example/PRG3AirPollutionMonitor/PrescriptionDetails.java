package com.example.PRG3AirPollutionMonitor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PrescriptionDetails extends AppCompatActivity {
    private TextView remaining_inhaler_uses_text;
    private TextView remaining_inhaler_uses_display;
    private TextView prescription_expiry_date_text;
    private TextView prescription_expiry_date_display;
    private TextView new_information_text;
    private EditText new_prescription_number_of_uses;
    private EditText new_prescription_expiry_date;
    private EditText new_prescription_dosage_interval;
    private String dosage_interval;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter [] inputFormatters = {
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("dd MM yyyy"),
            DateTimeFormatter.ofPattern("ddMMyyyy"),
            DateTimeFormatter.ofPattern("ddMMyy"),
            DateTimeFormatter.ofPattern("dd/MM/yy"),
            DateTimeFormatter.ofPattern("dd-MM-yy"),
            DateTimeFormatter.ofPattern("dd.MM.yy"),
            DateTimeFormatter.ofPattern("dd MM yy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("yyyy MM dd"),
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("yy/MM/dd"),
            DateTimeFormatter.ofPattern("yy-MM-dd"),
            DateTimeFormatter.ofPattern("yy.MM.dd"),
            DateTimeFormatter.ofPattern("yy MM dd"),
            DateTimeFormatter.ofPattern("yyMMdd"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("MM.dd.yyyy"),
            DateTimeFormatter.ofPattern("MM dd yyyy"),
            DateTimeFormatter.ofPattern("MMddyyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yy"),
            DateTimeFormatter.ofPattern("MM-dd-yy"),
            DateTimeFormatter.ofPattern("MM.dd.yy"),
            DateTimeFormatter.ofPattern("MM dd yy"),
            DateTimeFormatter.ofPattern("MMddyy"),
    };

    LocalDate dateNow = LocalDate.now();

    private Button save_button;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String TEXT2 = "text2";
    public static final String TEXT3 = "text3";


    private String text;
    private String text2;
    private String text3;

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
        new_prescription_dosage_interval = (EditText) findViewById(R.id.new_prescription_dosage_interval_input);
        new_information_text = (TextView) findViewById(R.id.new_information);
        save_button = (Button) findViewById(R.id.save_text_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalDate enteredDate = null;
                for (DateTimeFormatter formatter : inputFormatters) {
                    try {
                        enteredDate = LocalDate.parse(new_prescription_expiry_date.getText().toString(), formatter);
                        break;
                    } catch (DateTimeParseException e) {
                        // Ignore
                    }
                }
                if (enteredDate == null) {
                    Toast.makeText(PrescriptionDetails.this, getResources().getString(R.string.toast_error_date_format), Toast.LENGTH_SHORT).show();
                } else {
                      if (enteredDate.isAfter(dateNow)) {
                        if (Float.parseFloat(new_prescription_number_of_uses.getText().toString()) > 0) {
                            if (Float.parseFloat(new_prescription_dosage_interval.getText().toString()) > 0) {
                                remaining_inhaler_uses_text.setText(new_prescription_number_of_uses.getText().toString());
                                prescription_expiry_date_text.setText(enteredDate.format(dateFormatter));
                                dosage_interval = new_prescription_dosage_interval.getText().toString();
                                saveData();
                                Toast.makeText(PrescriptionDetails.this, getResources().getString(R.string.toast_prescription_saved), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(PrescriptionDetails.this, getResources().getString(R.string.toast_error_dosage_interval), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(PrescriptionDetails.this, getResources().getString(R.string.toast_error_no_of_uses), Toast.LENGTH_SHORT).show();
                        }
                      } else {
                          Toast.makeText(PrescriptionDetails.this, getResources().getString(R.string.toast_error_expiry_date), Toast.LENGTH_SHORT).show();
                    }
                }
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
        editor.putString(TEXT3, dosage_interval);
        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        text2 = sharedPreferences.getString(TEXT2, "");
        text3 = sharedPreferences.getString(TEXT3, "");
    }

    public void updateViews() {

        try {
            remaining_inhaler_uses_text.setText(text);
            prescription_expiry_date_text.setText(text2);

        } catch (Exception e) {
            System.out.println("Update View Error");
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        MainMenu.saveVar(this);
    }
}

