package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class BadAreas extends AppCompatActivity {
    String x;
    HashMap<String, String> values = new HashMap<String, String>();

    TextView sorted_areas_text_bad;
    static TextView list_of_areas_unsorted_text_2;
    static TextView numbers_of_areas_text_2;
    static TextView bad_areas_progress_text;
    static String all_areas;
    static String all_numbers;

    Button bad_areas_info_button;

    String[] items = {
            "Barking and Dagenham", "Barnet", "Bexley", "Brent", "Bromley", "Camden", "City of London", "Croydon",
            "Ealing", "Enfield", "Greenwich", "Hackney", "Hammersmith and Fulham", "Haringey", "Harrow", "Havering",
            "Hillingdon", "Hounslow", "Islington", "Kensington and Chelsea", "Kingston", "Lambeth", "Lewisham",
            "Merton", "Newham", "Redbridge", "Richmond", "Southwark", "Sutton", "Tower Hamlets", "Waltham Forest",
            "Wandsworth", "Westminster"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_areas);

        bad_areas_info_button = (Button) findViewById(R.id.bad_areas_info);
        bad_areas_info_button.setOnClickListener(v -> {
            Intent intent = new Intent(BadAreas.this, IndexInfo.class);
            startActivity(intent);
        });

        sorted_areas_text_bad = (TextView) findViewById(R.id.sorted_areas_bad);
        bad_areas_progress_text = (TextView) findViewById(R.id.bad_areas_progress);

        list_of_areas_unsorted_text_2 = findViewById(R.id.list_of_areas_unsorted_bad);
        numbers_of_areas_text_2 = findViewById(R.id.numbers_of_areas_bad);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    all_areas = "";
                    all_numbers = "";
                    for (int i = 0; i < 33; i++) {
                        bad_areas_progress_text.setText("Checked area " + i + " of " + 32);
                        if (i == 32) {
                            bad_areas_progress_text.setText("");
                        }
                        System.out.println(i);
                        x = items[i].toLowerCase().replaceAll("\\s+", "");
                        String data = "";
                        getAQI getInformation = new getAQI(x, data);
                        String[] y = getInformation.AQIOutput();
                        System.out.println(y[1]);
                        if (Integer.parseInt(y[1]) == 0) {
                            y[1] = "No Info";
                        } else {

                            System.out.println("This happened");
                        }

                        System.out.println(values.get(x));
                        try {
                            //Same as SortedAreas activity, but only includes areas that have a higher risk to asthma sufferers
                            if (Integer.parseInt(y[1]) >= 2) {
                                y[1] = "\uD83D\uDD34";
                                values.put(x, y[1]);
                                all_areas = all_areas + items[i] + "\n";
                                all_numbers = all_numbers + values.get(x) + "\n";
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        list_of_areas_unsorted_text_2.setText(all_areas);
                                        numbers_of_areas_text_2.setText(all_numbers);
                                    }
                                });
                            } else {
                            }
                        } catch (NumberFormatException n) {
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}