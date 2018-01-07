package com.glassy.salesmanager.Stats;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glassy.salesmanager.Sale.Sale;
import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by glassy on 1/6/18.
 */

public class StatsModel {
    public static final int FIRST_WEEK = 1;
    public static final int SECOND_WEEK = 8;
    public static final int THIRD_WEEK = 15;
    public static final int FOURTH_WEEK = 22;

    final protected StatsEvents events;
    private SQLiteDatabase db;
    DebtCollectionDBHelper dbHelper;

    public StatsModel(StatsEvents events) {
        this.events = events;
        dbHelper = new DebtCollectionDBHelper(events.getAppContext());
    }

    public void getSalesOfMonth(int year, int month) {
        events.countSales(getSales());
    }


    //SELECT count(*) FROM Sale WHERE SaleDate BETWEEN date('2018-01-01','start of month','+21 day') and date('2018-01-01','start of month', '+1 month', '-1 day')
    //SELECT COUNT(*) FROM Sale WHERE SaleDate BETWEEN date('2018-01-01','start of month','+1 day') and date('2018-01-01','start of month', '+8 day')


    public String getStringQueryDate(int week){
        if (week == FOURTH_WEEK){
            return "'start of month', '+1 month', '-1 day')";
        }
        else {
            return "'start of month', '+8 day')";
        }
    }
    //yyyy-MM-dd HH:mm:ss
    public ArrayList<Sale> getSales(){
        ArrayList<Sale> sales = new ArrayList<>();
        db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " +
                DebtCollectionContract.Sale.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0 ){
            while (cursor.moveToNext()){
                sales.add(new Sale(
                        Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Sale.COLUMN_DATE)))
                ));
            }
        }
        db.close();
        return sales;
    }
}
