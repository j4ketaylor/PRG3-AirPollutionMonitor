package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.Button;
import android.widget.TextView;

public class PlotInfo extends AppCompatActivity {

    TextView plot_description_text, plot_description_text2;

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
        plot_description_text.setText("The graphs are plotted using data from the London Air Quality Network Summary" +
                " Reports between 2015 and 2020 [1]. Several areas are left out due to limited data avalibility in" +
                "the interval.\nThe graphs can be used to help with choosing where to live in London, however, we " +
                "recommend asthma sufferers to live as far away as possible from major roadways [2].");

        plot_description_text2 = (TextView) findViewById(R.id.plot_description2);
        String reference_links1 = "\nReferences: \n[1]" + " https://www.londonair.org.uk/london/asp/reports.asp\n" +
                "[2] Guarnieri M, Balmes JR. Outdoor " +
                "air pollution and asthma. Lancet. 2014 May 3;383(9928):1581-92. doi: " +
                "10.1016/S0140-6736(14)60617-6. PMID: 24792855; PMCID: PMC4465283\n";
        plot_description_text2.setText(reference_links1);
        Linkify.addLinks(plot_description_text2,Linkify.WEB_URLS);


    }
}