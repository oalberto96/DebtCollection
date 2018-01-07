package com.glassy.salesmanager.Stats;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by glassy on 1/6/18.
 */

public class StatsPresenter implements StatsEvents {
    private StatsView view;
    private StatsModel model;

    StatsPresenter(StatsView view){
        this.view = view;
        this.model = new StatsModel(this);
    }

    @Override
    public Context getAppContext() {
        return view.getAppContext();
    }

    @Override
    public void saleCountSuccess(ArrayList<Integer> saleCount) {
        view.saleCountSuccess(saleCount);
    }

    public void getSalesOfMonth(int year, int month) {
        model.getSalesOfMonth(year,month);
    }
}
