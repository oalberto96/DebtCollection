package com.glassy.salesmanager.Stats;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.Toast;

import com.glassy.salesmanager.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;



public class StatsActivity extends AppCompatActivity implements StatsView {
    private StatsPresenter presenter;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        presenter = new StatsPresenter(this);
        graph = (GraphView) findViewById(R.id.graph);
        graphInit();

        test();
    }

    @Override
    public Context getAppContext() {
        return this;
    }

    @Override
    public void saleCountSuccess(ArrayList<Integer> saleCount) {
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        for(int i=0; i < saleCount.size(); i++){
            dataPoints.add(new DataPoint(i, saleCount.get(i)));
        }
        bindDataGraph(dataPoints);
    }

    private void bindDataGraph(ArrayList<DataPoint> dataPoints) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints.toArray(new DataPoint[dataPoints.size() -1]));
        graph.addSeries(series);
        GraphUtils.setXYLabels(graph,
                new String[] {getString(R.string.first_week),
                        getString(R.string.second_week),
                        getString(R.string.third_week),
                        getString(R.string.fourth_week)},
                new String[] {
                        String.valueOf(dataPoints.get(0).getY()),
                        String.valueOf(dataPoints.get(1).getY()),
                        String.valueOf(dataPoints.get(2).getY()),
                        String.valueOf(dataPoints.get(3).getY()),
                        });

    }

    private void getCountSalesOfMonth(int year, int month){
        try {
            presenter.getSalesOfMonth(year,month);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void test(){
        getCountSalesOfMonth(2018,01);
    }


    private void graphInit(){

    }
}
