package com.example.PRG3AirPollutionMonitor;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class AirQualityPlot extends AppCompatActivity {

    TextView question3;
    LineChart testchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_quality_plot);

        testchart=(LineChart) findViewById(R.id.line_chart);
        LineDataSet testlinedataset = new LineDataSet(testdata(),"Test Data 1");
        ArrayList<ILineDataSet> d = new ArrayList<>();
        d.add(testlinedataset);

        LineData testdata = new LineData(d);
        testchart.setData(testdata);
        testchart.invalidate();

        question3 = (TextView) findViewById(R.id.question3_id);
        question3.setText("Which area would you like to view history for?\n");

    }
    private ArrayList<Entry> testdata(){
        ArrayList<Entry> t = new ArrayList<Entry>();
        t.add(new Entry(0,20));
        t.add(new Entry(1,25));
        t.add(new Entry(5,15));
        t.add(new Entry(22,7));
        t.add(new Entry(-1,9)); //Need to convert double to float if needed

        return t;
    }
}