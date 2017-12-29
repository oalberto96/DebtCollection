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
        dbHelper = new DebtCollectionDBHelper(events.getContext());
    }

    public void loadProductsList(){
        events.loadProductsList(getProducts());
    }

    public void createProduct(Product product){
        db = dbHelper.getWritableDatabase();
        String dbInsert = "INSERT INTO " + DebtCollectionContract.Product.TABLE_NAME + " (" +
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
        events.addNewProductSuccess();
    }

    public void deleteClient(int id){
        db = dbHelper.getWritableDatabase();
        String SQLScript = "DELETE FROM " + DebtCollectionContract.Product.TABLE_NAME +
                " WHERE _id = " + id;
        db.execSQL(SQLScript);
        db.close();
        events.addNewProductSuccess();
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<Product>();
        db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " +
                DebtCollectionContract.Product.TABLE_NAME+";";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                products.add(new Product(
                        cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.Product._ID)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_NAME)),
                        cursor.getFloat(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_PRICE)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_COLOR)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_SIZE)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_MATERIAL))
                ));
            }
        }
        db.close();
        return products;
    }
}
