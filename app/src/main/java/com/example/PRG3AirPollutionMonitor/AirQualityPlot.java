package com.example.PRG3AirPollutionMonitor;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AirQualityPlot extends AppCompatActivity {

    TextView question3;
    LineChart testchart;
    TextView xlabel;
    TextView ylabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality_plot);

        testchart=(LineChart) findViewById(R.id.line_chart);
        LineDataSet testlinedataset = new LineDataSet(testdata(),"Air Quality for Westminster");
        ArrayList<ILineDataSet> d = new ArrayList<>();
        d.add(testlinedataset);

        LineData testdata = new LineData(d);
        testchart.setData(testdata);
        testchart.invalidate();

        question3 = (TextView) findViewById(R.id.question3_id);
        question3.setText("Which area would you like to view history for?\n");

        xlabel = (TextView) findViewById(R.id.xlabel_id);
        xlabel.setText("Year");

        ylabel = (TextView) findViewById(R.id.ylabel_id);
        ylabel.setText("Asthma Index");

        configureLineChart();

    }
    private ArrayList<Entry> testdata(){
        ArrayList<Entry> t = new ArrayList<Entry>();
        t.add(new Entry(0,25));
        t.add(new Entry(1,23));
        t.add(new Entry(2,21));
        t.add(new Entry(3,20));
        t.add(new Entry(4,19)); //Need to convert double to float if needed

        return t;
    }

    private void configureLineChart() {
        Description desc = new Description();
        desc.setText("Air Quality History");
        desc.setTextSize(8);
        testchart.setDescription(desc);

        XAxis xAxis = testchart.getXAxis();

        String[] xAxisLables = new String[]{"2014","2015","2016","2017","2018"};

        testchart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
    }

}