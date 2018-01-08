package com.glassy.salesmanager.Stats;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;

import java.util.ArrayList;

/**
 * Created by glassy on 1/6/18.
 */

public class GraphUtils {

    public static void setXYLabels(GraphView graph, String[] xLabel, String[] yLabel){
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(xLabel);
        staticLabelsFormatter.setVerticalLabels(yLabel);
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
    }

}
