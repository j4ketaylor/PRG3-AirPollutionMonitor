package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IndexInfo extends AppCompatActivity {

    TextView colour_text, index_description_text1, index_description_text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_info);

        colour_text = (TextView) findViewById(R.id.colour_key);
        colour_text.setText(" \uD83D\uDFE2 Good?\n \uD83D\uDFE0 Dangerous?");

        index_description_text1 = (TextView) findViewById(R.id.index_description1);
        index_description_text1.setText("The asthma index is used to quantify the effects of pollution for asthma sufferers.");

        index_description_text2 = (TextView) findViewById(R.id.index_description2);
        index_description_text2.setText("Based off research, the following pollutants were weighted with respect to their properties to increase the risk of asthma attacks.\nThe asthma index is calculated using live data of Defra's Air Pollution Index provided by London Air's API.\n");
    }
}