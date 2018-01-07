package com.glassy.salesmanager.Stats;

import android.content.Context;
import android.content.Intent;

import com.glassy.salesmanager.Sale.Sale;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by glassy on 1/6/18.
 */

public class StatsPresenter implements StatsEvents {
    private StatsView view;
    private StatsModel model;
    Date initDate;
    Date finalDate;
    SimpleDateFormat dateFormat;


    StatsPresenter(StatsView view){
        this.view = view;
        this.model = new StatsModel(this);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public Context getAppContext() {
        return view.getAppContext();
    }

    @Override
    public void saleCountSuccess(ArrayList<Integer> saleCount) {
        view.saleCountSuccess(saleCount);
    }

    @Override
    public void countSales(ArrayList<Sale> sales) {
        ArrayList<Integer> saleCount = new ArrayList<>();
        Calendar saleDate = Calendar.getInstance();
        int countWeek1 = 0, countWeek2 = 0, countWeek3 = 0, countWeek4 = 0;

        for (Sale sale:sales){
            saleDate.setTime(sale.getDateSale());
            if (sale.getDateSale().before(finalDate) && sale.getDateSale().after(initDate)){
                switch (saleDate.get(Calendar.DAY_OF_WEEK_IN_MONTH)){
                    case 1:
                        countWeek1++;
                        break;
                    case 2:
                        countWeek2++;
                        break;
                    case 3:
                        countWeek3++;
                        break;
                    case 4:
                        countWeek4++;
                        break;
                }
            }
        }
        saleCount.add(countWeek1);
        saleCount.add(countWeek2);
        saleCount.add(countWeek3);
        saleCount.add(countWeek4);
        saleCountSuccess(saleCount);
    }

    //Todo change date limit
    public void getSalesOfMonth(int year, int month) throws ParseException {
        initDate = dateFormat.parse(getStringDate(year, month) + "-01");
        finalDate = dateFormat.parse(getStringDate(year, month) + "-28");
        model.getSalesOfMonth(year,month);
    }

    public String getStringDate(int year, int month){
        if (month < 10){
            return year + "-0" + month;
        }
        else {
            return year + "-" + month;
        }
    }
}
