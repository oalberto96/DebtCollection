package com.glassy.salesmanager.MVP.Models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glassy.salesmanager.Events.ProductEvents;
import com.glassy.salesmanager.Events.SaleEvents;
import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by glassy on 12/19/17.
 */

public class SaleModel {
    final protected SaleEvents events;
    private SQLiteDatabase db;
    DebtCollectionDBHelper dbHelper;

    public SaleModel(SaleEvents events) {
        this.events = events;
        dbHelper = new DebtCollectionDBHelper(events.getContext());
    }

    public void loadProductsList(){
        events.loadProductsList(getSales());
    }

    public void saleProduct(Sale sale){
        //Sql
    }

    public void deleteClient(int id){
        //sql
    }

    public ArrayList<Sale> getSales(){
        ArrayList<Sale> sales = new ArrayList<Sale>();
        return sales;
    }
}
