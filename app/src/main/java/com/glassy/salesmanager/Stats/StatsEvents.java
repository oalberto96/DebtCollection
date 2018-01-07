package com.glassy.salesmanager.Stats;

import android.content.Context;

import com.glassy.salesmanager.Sale.Sale;

import java.util.ArrayList;

/**
 * Created by glassy on 1/6/18.
 */

public interface StatsEvents {
    Context getAppContext();

    void saleCountSuccess(ArrayList<Integer> saleCount);

    void countSales(ArrayList<Sale> sales);
}
