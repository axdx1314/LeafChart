package com.beiing.leafchartdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.beiing.leafchart.MoveSelectLineChart;
import com.beiing.leafchart.bean.Axis;
import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.beiing.leafchart.support.OnPointSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MoveSelectLineChartActivity extends AppCompatActivity {

    MoveSelectLineChart moveSelectLineChart;
    TextView tvSelectPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_select_line_chart);
        moveSelectLineChart = (MoveSelectLineChart) findViewById(R.id.move_select_chart);
        tvSelectPoint = (TextView) findViewById(R.id.tv_select_point);

        initLineChart();

        moveSelectLineChart.setOnPointSelectListener(new OnPointSelectListener() {
            @Override
            public void onPointSelect(int position, String xLabel, String value) {
                String point = xLabel + ":" + value;
                tvSelectPoint.setText(point);
            }
        });
    }

    private void initLineChart() {
        Axis axisX = new Axis(getAxisValuesX());
        axisX.setAxisColor(Color.parseColor("#33B5E5")).setTextColor(Color.DKGRAY).setHasLines(false).setShowText(false);
        Axis axisY = new Axis(getAxisValuesY());
        axisY.setAxisColor(Color.parseColor("#33B5E5")).setTextColor(Color.DKGRAY).setHasLines(false).setShowText(true);
        moveSelectLineChart.setAxisX(axisX);
        moveSelectLineChart.setAxisY(axisY);

        moveSelectLineChart.setChartData(getFoldLine());
        moveSelectLineChart.show();
    }


    private List<AxisValue> getAxisValuesX(){
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            AxisValue value = new AxisValue();
            value.setLabel(i + "日");
            axisValues.add(value);
        }
        return axisValues;
    }

    private List<AxisValue> getAxisValuesY(){
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            AxisValue value = new AxisValue();
            value.setLabel(String.valueOf(i * 10));
            axisValues.add(value);
        }
        return axisValues;
    }

    private Line getFoldLine(){
        List<PointValue> pointValues = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            PointValue pointValue = new PointValue();
            pointValue.setX( (i - 1) / (31f - 1f));
            int var = (int) (Math.random() * 100);
            pointValue.setLabel(String.valueOf(var));
            pointValue.setY(var / 100f);
            pointValues.add(pointValue);
        }

        Line line = new Line(pointValues);
        line.setLineColor(Color.parseColor("#33B5E5"))
                .setLineWidth(1.5f)
                .setPointColor(Color.parseColor("#33B5E5"))
                .setCubic(true)
                .setPointRadius(2)
                .setFill(true)
                .setFillColr(Color.parseColor("#33B5E5"))
                .setOpenMoveSelect(true)
                .setMoveLineColor(Color.MAGENTA)
                .setLabelColor(Color.parseColor("#33B5E5"));
        return line;
    }
}