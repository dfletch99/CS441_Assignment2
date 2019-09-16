package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText slope = (EditText) findViewById(R.id.slope);
        final EditText intercept = (EditText) findViewById(R.id.intercept);
        final GraphView graph = findViewById(R.id.graph);
        final LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double x, y, cross;
                x = -100.0;
                cross = Double.parseDouble(intercept.getText().toString());
                for(int i = 0; i < 2000; i++){
                    double slope_num = Double.parseDouble(slope.getText().toString());
                    y = slope_num * x + cross;
                    series.appendData(new DataPoint(x, y), true, 2000);
                    x += 0.1;
                }
                graph.addSeries(series);
            }
        });
    }


}
