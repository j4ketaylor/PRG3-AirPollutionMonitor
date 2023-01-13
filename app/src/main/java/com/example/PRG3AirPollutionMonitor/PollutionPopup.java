package com.example.PRG3AirPollutionMonitor;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.HashMap;

public class PollutionPopup extends Activity {
    static TextView areas_to_avoid_start_text;
    static String x;
    static String all_areas2;
    private View popup = null;
    private LayoutInflater nlayoutInflater;

    String[] items = {
            "Barking and Dagenham", "Barnet", "Bexley", "Brent", "Bromley", "Camden", "City of London", "Croydon",
            "Ealing", "Enfield", "Greenwich", "Hackney", "Hammersmith and Fulham", "Haringey", "Harrow", "Havering",
            "Hillingdon", "Hounslow", "Islington", "Kensington and Chelsea", "Kingston", "Lambeth", "Lewisham",
            "Merton", "Newham", "Redbridge", "Richmond", "Southwark", "Sutton", "Tower Hamlets", "Waltham Forest",
            "Wandsworth", "Westminster"
    };

    HashMap<String, String> values = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollution_popup);
        nlayoutInflater = LayoutInflater.from(PollutionPopup.this);
        popup = nlayoutInflater.inflate(R.layout.activity_pollution_popup,null);
        areas_to_avoid_start_text = (TextView) findViewById(R.id.list_of_areas_main_menu);
        //sets size of popup
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.7),(int)(height*0.5));

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                all_areas2 = "Areas to avoid:" + "\n";
                try  {
                    for (int i = 0; i < 33; i++) {
                        x = items[i].toLowerCase().replaceAll("\\s+", "");
                        String data = "";
                        getAQI getInformation = new getAQI(x, data);
                        String[] y = getInformation.AQIOutput();
                        if (Integer.parseInt(y[1]) == 0) {
                            y[1] = "No Info";
                        } else {
                            System.out.println("This happened");
                        }

                        System.out.println(values.get(x));
                        try {
                            if (Integer.parseInt(y[1]) >= 2) {
                                y[1] = "\uD83D\uDFE0";
                                values.put(x, y[1]);
                                all_areas2 = all_areas2 + items[i] + "\n";
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println(all_areas2);
                                        areas_to_avoid_start_text.setText(all_areas2);
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
