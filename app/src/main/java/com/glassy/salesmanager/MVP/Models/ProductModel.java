package com.glassy.salesmanager.MVP.Models;

import android.database.sqlite.SQLiteDatabase;

import com.glassy.salesmanager.Events.ProductEvents;
import com.glassy.salesmanager.Events.UserEvents;
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
        dbHelper = new DebtCollectionDBHelper(events.getContext());
    }

    public void createProduct(Product product){
        db = dbHelper.getWritableDatabase();
        String dbInsert = "INSERT INTO " + DebtCollectionContract.Client.TABLE_NAME + " (" +
                DebtCollectionContract.Product.COLUMN_NAME + ", " +
                DebtCollectionContract.Product.COLUMN_PRICE + ", " +
                DebtCollectionContract.Product.COLUMN_COLOR + ", " +
                DebtCollectionContract.Product.COLUMN_SIZE + ", " +
                DebtCollectionContract.Product.COLUMN_MATERIAL + " " +
                ") VALUES (" + "\"" +
                product.getName() + "\", \"" +
                product.getPrice() + "\", \"" +
                product.getColor() + "\", \"" +
                product.getSize() + "\", \"" +
                product.getMaterial() +
                "\");";

        db.execSQL(dbInsert);
        db.close();
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<Product>();
        return products;
    }
}
