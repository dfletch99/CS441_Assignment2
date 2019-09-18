package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.*;


public class MainActivity extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView firstX = findViewById(R.id.x_plus_first);
        final TextView secondX = findViewById(R.id.x_plus_second);
        final TextView thirdX = findViewById(R.id.x_plus_third);
        final TextView fourthX = findViewById(R.id.x_plus_fourth);

        final EditText slope = findViewById(R.id.fourth_degree);
        final EditText intercept = findViewById(R.id.third_degree);
        final GraphView graph = findViewById(R.id.graph);

        final Button graphButton = findViewById(R.id.graphButton);
        final Button clearButton = findViewById(R.id.clearButton);
        final Button linearButton = findViewById(R.id.linearButton);
        final Button quadraticButton = findViewById(R.id.quadraticButton);
        final Button cubicButton = findViewById(R.id.cubicButton);
        final Button quarticButton = findViewById(R.id.quarticButton);

        graphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                series = new LineGraphSeries<DataPoint>();
                double x, y, cross, slope_num;

                if(TextUtils.isEmpty(slope.getText())) slope_num = 1;
                else slope_num = Double.parseDouble(slope.getText().toString());

                if(TextUtils.isEmpty(intercept.getText())) cross = 0;
                else cross = Double.parseDouble(intercept.getText().toString());

                x = -100.0;

                for(int i = 0; i < 2000; i++){

                    y = slope_num * x + cross;
                    series.appendData(new DataPoint(x, y), true, 2000);
                    x += 0.1;
                }
                graph.addSeries(series);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                slope.getText().clear();
                intercept.getText().clear();
                graph.removeAllSeries();
            }
        });


    }


}
