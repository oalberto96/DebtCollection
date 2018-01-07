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
            dataPoints.add(new DataPoint(i + 1, saleCount.get(i)));
        }
        bindDataGraph(dataPoints);
    }

    private void bindDataGraph(ArrayList<DataPoint> dataPoints) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints.toArray(new DataPoint[dataPoints.size()]));
        graph.addSeries(series);
        GraphUtils.setXYLabels(graph,new String[] {"low", "middle", "high"},new String[] {"low", "middle", "high"});

    }

    private void getCountSalesOfMonth(int year, int month){
        presenter.getSalesOfMonth(year,month);
    }

    public void test(){
        getCountSalesOfMonth(2018,01);
    }


    private void graphInit(){

    }
}
