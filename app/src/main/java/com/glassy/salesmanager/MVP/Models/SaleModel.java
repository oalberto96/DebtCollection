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

    public void loadSaleList(){
        events.loadSalesList(getSales());
    }

    public void loadProducts(){
        events.loadProductsSucces(getProducts());
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

    public void loadClients(){
        events.loadClientsSuccess(getClients());
    }

    public ArrayList<Client> getClients(){
        db = dbHelper.getWritableDatabase();
        ArrayList<Client> clients = new ArrayList<Client>();
        String query = "SELECT * FROM " +
                DebtCollectionContract.Client.TABLE_NAME+";";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                clients.add(new Client(
                        cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.Client._ID)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_FIRST_NAME)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_LAST_NAME)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_PHONE_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_NOTES)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_TIN))
                ));
            }
        }
        db.close();
        return clients;
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
