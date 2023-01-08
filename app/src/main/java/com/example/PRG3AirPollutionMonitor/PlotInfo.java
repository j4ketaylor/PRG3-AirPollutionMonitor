package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PlotInfo extends AppCompatActivity {

    TextView plot_description_text;

    Button plot_info_to_index_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_info);

        plot_info_to_index_button = (Button) findViewById(R.id.plot_info_to_index);
        plot_info_to_index_button.setOnClickListener(v -> {
            Intent intent = new Intent(PlotInfo.this, IndexInfo.class);
            startActivity(intent);
        });

        plot_description_text = (TextView) findViewById(R.id.plot_description);
        plot_description_text.setText(" The graphs are plotted using data form report....");
    }
}