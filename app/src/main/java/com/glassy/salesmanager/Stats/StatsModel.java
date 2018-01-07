package com.glassy.salesmanager.Stats;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

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
        ArrayList<Integer> saleCount = new ArrayList<>();
        saleCount.add(getSalesOfWeek(getStringDate(year,month),FIRST_WEEK));
        saleCount.add(getSalesOfWeek(getStringDate(year,month),SECOND_WEEK));
        saleCount.add(getSalesOfWeek(getStringDate(year,month),THIRD_WEEK));
        saleCount.add(getSalesOfWeek(getStringDate(year,month),FOURTH_WEEK));
        events.saleCountSuccess(saleCount);
    }


    //SELECT count(*) FROM Sale WHERE SaleDate BETWEEN date('2018-01-01','start of month','+21 day') and date('2018-01-01','start of month', '+1 month', '-1 day')
    //SELECT COUNT(*) FROM Sale WHERE SaleDate BETWEEN date('2018-01-01','start of month','+1 day') and date('2018-01-01','start of month', '+8 day')

    public String getStringDate(int year, int month){
        if (month < 10){
            return year + "-0" + month + "-01" + " 00:00:00.0";
        }
        else {
            return year + "-" + month + "-01" + " 00:00:00.0";
        }
    }

    public String getStringQueryDate(int week){
        if (week == FOURTH_WEEK){
            return "'start of month', '+1 month', '-1 day')";
        }
        else {
            return "'start of month', '+8 day')";
        }
    }
    //yyyy-MM-dd HH:mm:ss
    public int getSalesOfWeek(String date, int week){
        int count = 0;
        db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " +
                DebtCollectionContract.Sale.TABLE_NAME + " " +
                "WHERE " + DebtCollectionContract.Sale.COLUMN_DATE + " " +
                "BETWEEN date('" +
                date + "', 'start of month' ," +
                "'+" + week + " day') " +
                "and date('" +
                date + "'," + getStringQueryDate(week) + ";" ;
        Cursor cursor = db.rawQuery(query,null);
        count = cursor.getCount();
        db.close();
        return count;
    }
}
