package com.glassy.salesmanager.Product;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public class ProductModel {
    final protected ProductEvents events;
    private SQLiteDatabase db;
    DebtCollectionDBHelper dbHelper;

    public ProductModel(ProductEvents events) {
        this.events = events;
        dbHelper = new DebtCollectionDBHelper(this.events.getAppContext());
    }

    public void loadProducts(){
        events.loadProductsListSuccess(dbHelper.getProducts());
    }



    public void deleteProduct(int id){
        dbHelper.deleteProduct(id);
        events.loadProductsListSuccess(dbHelper.getProducts());
    }






}
