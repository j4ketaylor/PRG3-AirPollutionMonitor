package com.example.PRG3AirPollutionMonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IndexInfo extends AppCompatActivity {

    TextView colour_text, index_description_text1, index_description_text2, index_description_text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_info);

        colour_text = (TextView) findViewById(R.id.colour_key);
        colour_text.setText(" \uD83D\uDFE2 Low Risk\n \uD83D\uDD34 High Risk");

        index_description_text1 = (TextView) findViewById(R.id.index_description1);
        index_description_text1.setText("The asthma index is used to quantify the effects of pollution for asthma sufferers.");

        index_description_text2 = (TextView) findViewById(R.id.index_description2);
        index_description_text2.setText("Based off research [1], the following pollutants " +
                "were weighted with respect to their properties to increase the risk of asthma attacks." +
                "\nThe asthma index is calculated using live data of Defra's Air Pollution Index [2] provided by London Air's API.\n");

        index_description_text3 = (TextView) findViewById(R.id.index_description3);
        String reference_links = "\nReferences: \n[1] Guarnieri M, Balmes JR. Outdoor " +
                "air pollution and asthma. Lancet. 2014 May 3;383(9928):1581-92. doi: " +
                "10.1016/S0140-6736(14)60617-6. PMID: 24792855; PMCID: PMC4465283\n[2] "+
                "https://www.londonair.org.uk/london/asp/airpollutionindex.asp?la_id=&region=0&bulletin=hourly&site=&bulletindate=10/01/2023%2015:00:00&level=All&MapType=Google&VenueCode=\n";
        index_description_text3.setText(reference_links);
        Linkify.addLinks(index_description_text3,Linkify.WEB_URLS);
    }

}