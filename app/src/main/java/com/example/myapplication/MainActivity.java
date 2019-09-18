package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.*;


public class MainActivity extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;
    boolean isLinear = true, isQuadratic = false, isCubic = false, isQuartic = false;
    int lineCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView firstX = findViewById(R.id.x_plus_first);
        final TextView secondX = findViewById(R.id.x_plus_second);
        final TextView thirdX = findViewById(R.id.x_plus_third);
        final TextView title = findViewById(R.id.title);

        final TextView power4 = findViewById(R.id.power4);
        final TextView power3 = findViewById(R.id.power3);
        final TextView power2 = findViewById(R.id.power2);

        final EditText fourth = findViewById(R.id.fourth_degree);
        final EditText third = findViewById(R.id.third_degree);
        final EditText second = findViewById(R.id.second_degree);
        final EditText first = findViewById(R.id.first_degree);
        final EditText constant = findViewById(R.id.constant);

        final GraphView graph = findViewById(R.id.graph);

        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);

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
                double x, y;
                double fourth_degree_num = 0, third_degree_num = 0, second_degree_num = 0, first_degree_num = 1, constant_num = 0;
                if(isQuartic){
                    if(TextUtils.isEmpty(fourth.getText())) fourth_degree_num = 1;
                    else if(fourth.getText().toString().equals("-")) fourth_degree_num = -1;
                    else fourth_degree_num = Double.parseDouble(fourth.getText().toString());
                }

                if(isQuartic || isCubic){
                    if(TextUtils.isEmpty(third.getText())) third_degree_num = 1;
                    else if(third.getText().toString().equals("-")) third_degree_num = -1;
                    else third_degree_num = Double.parseDouble(third.getText().toString());
                }

                if(isQuartic || isCubic || isQuadratic){
                    if(TextUtils.isEmpty(second.getText())) second_degree_num = 1;
                    else if(second.getText().toString().equals("-")) second_degree_num = -1;
                    else second_degree_num = Double.parseDouble(second.getText().toString());
                }

                if(TextUtils.isEmpty(first.getText())) first_degree_num = 1;
                else if(first.getText().toString().equals("-")) first_degree_num = -1;
                else first_degree_num = Double.parseDouble(first.getText().toString());

                if(TextUtils.isEmpty(constant.getText())) constant_num = 0;
                else if(constant.getText().toString().equals("-")) constant_num = 0;
                else constant_num = Double.parseDouble(constant.getText().toString());

                x = -100.0;
                for(int i = 0; i < 2000; i++){
                    y = (fourth_degree_num * Math.pow(x, 4)) + (third_degree_num * Math.pow(x, 3)) + (second_degree_num * Math.pow(x, 2)) + (first_degree_num * x) + constant_num;
                    series.appendData(new DataPoint(x, y), true, 2000);
                    x += 0.1;
                }
                switch(lineCounter % 6){
                    case 0:
                        series.setColor(Color.BLACK);
                        break;
                    case 1:
                        series.setColor(Color.RED);
                        break;
                    case 2:
                        series.setColor(Color.BLUE);
                        break;
                    case 3:
                        series.setColor(Color.GREEN);
                        break;
                    case 4:
                        series.setColor(0xffff00ff);
                        break;
                    case 5:
                        series.setColor(Color.YELLOW);
                        break;
                }
                graph.addSeries(series);
                lineCounter++;
                series.setOnDataPointTapListener(new OnDataPointTapListener() {
                    @Override
                    public void onTap(Series series, DataPointInterface dataPoint) {
                        String msg = "X: " + Math.round(dataPoint.getX()) + "\nY: " + Math.round(dataPoint.getY());
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                graph.removeAllSeries();
                lineCounter = 0;
            }
        });

        linearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isLinear = true;
                isQuadratic = false;
                isCubic = false;
                isQuartic = false;

                title.setText("Enter your linear function");

                fourth.setVisibility(View.INVISIBLE);
                third.setVisibility(View.INVISIBLE);
                second.setVisibility(View.INVISIBLE);

                firstX.setVisibility(View.INVISIBLE);
                secondX.setVisibility(View.INVISIBLE);
                thirdX.setVisibility(View.INVISIBLE);

                power4.setVisibility(View.INVISIBLE);
                power3.setVisibility(View.INVISIBLE);
                power2.setVisibility(View.INVISIBLE);
            }
        });

        quadraticButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isLinear = false;
                isQuadratic = true;
                isCubic = false;
                isQuartic = false;

                title.setText("Enter your quadratic function:");

                fourth.setVisibility(View.INVISIBLE);
                third.setVisibility(View.INVISIBLE);
                second.setVisibility(View.VISIBLE);

                firstX.setVisibility(View.INVISIBLE);
                secondX.setVisibility(View.INVISIBLE);
                thirdX.setVisibility(View.VISIBLE);

                power4.setVisibility(View.INVISIBLE);
                power3.setVisibility(View.INVISIBLE);
                power2.setVisibility(View.VISIBLE);
            }
        });

        cubicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isLinear = false;
                isQuadratic = false;
                isCubic = true;
                isQuartic = false;

                title.setText("Enter your cubic function:");

                fourth.setVisibility(View.INVISIBLE);
                third.setVisibility(View.VISIBLE);
                second.setVisibility(View.VISIBLE);

                firstX.setVisibility(View.INVISIBLE);
                secondX.setVisibility(View.VISIBLE);
                thirdX.setVisibility(View.VISIBLE);

                power4.setVisibility(View.INVISIBLE);
                power3.setVisibility(View.VISIBLE);
                power2.setVisibility(View.VISIBLE);
            }
        });

        quarticButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isLinear = false;
                isQuadratic = false;
                isCubic = false;
                isQuartic = true;

                title.setText("Enter your quartic function:");

                fourth.setVisibility(View.VISIBLE);
                third.setVisibility(View.VISIBLE);
                second.setVisibility(View.VISIBLE);

                firstX.setVisibility(View.VISIBLE);
                secondX.setVisibility(View.VISIBLE);
                thirdX.setVisibility(View.VISIBLE);

                power4.setVisibility(View.VISIBLE);
                power3.setVisibility(View.VISIBLE);
                power2.setVisibility(View.VISIBLE);
            }
        });
    }


}
