package com.glassy.salesmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.glassy.salesmanager.Client.Client;

import java.util.ArrayList;

/**
 * Created by glassy on 12/17/17.
 */

public class DebtCollectionDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "debtcollection.db";

    private static final int DATABASE_VERSION = 1;

    public DebtCollectionDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_DEBT_COLLECTION_DATABASE = "CREATE TABLE " +
                DebtCollectionContract.Client.TABLE_NAME + " ( " +
                DebtCollectionContract.Client._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DebtCollectionContract.Client.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                DebtCollectionContract.Client.COLUMN_LAST_NAME + " TEXT, " +
                DebtCollectionContract.Client.COLUMN_ADDRESS + " TEXT, " +
                DebtCollectionContract.Client.COLUMN_PHONE_NUMBER + " TEXT, " +
                DebtCollectionContract.Client.COLUMN_NOTES + " TEXT, " +
                DebtCollectionContract.Client.COLUMN_TIN + " TEXT " +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_DEBT_COLLECTION_DATABASE);
        final String SQL_CREATE_DEBT_COLLECTION_DATABASE_PRODUCTS =
                "CREATE TABLE " +
                        DebtCollectionContract.Product.TABLE_NAME + " ( " +
                        DebtCollectionContract.Product._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DebtCollectionContract.Product.COLUMN_NAME + " TEXT NOT NULL, " +
                        DebtCollectionContract.Product.COLUMN_PRICE + " REAL, " +
                        DebtCollectionContract.Product.COLUMN_COLOR + " TEXT, " +
                        DebtCollectionContract.Product.COLUMN_SIZE + " TEXT, " +
                        DebtCollectionContract.Product.COLUMN_MATERIAL + " TEXT " +
                        ");"
                ;
        sqLiteDatabase.execSQL(SQL_CREATE_DEBT_COLLECTION_DATABASE_PRODUCTS);
        final String SQL_CREATE_DEBT_COLLECTION_DATABASE_SALES =
                "CREATE TABLE " +
                        DebtCollectionContract.Sale.TABLE_NAME + " ( " +
                        DebtCollectionContract.Sale._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DebtCollectionContract.Sale.CLIENT_ID + " INTEGER NOT NULL, " +
                        DebtCollectionContract.Sale.COLUMN_NAME + " TEXT NOT NULL, " +
                        DebtCollectionContract.Sale.COLUMN_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                        ");"
                ;
        sqLiteDatabase.execSQL(SQL_CREATE_DEBT_COLLECTION_DATABASE_SALES);

        final String SQL_CREATE_DEBT_COLLECTION_DATABASE_SALES_PRODUCT =
                "CREATE TABLE " +
                        DebtCollectionContract.SaleProduct.TABLE_NAME + " ( " +
                        DebtCollectionContract.SaleProduct._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        DebtCollectionContract.SaleProduct.COLUMN_SALE_ID + " INTEGER NOT NULL, " +
                        DebtCollectionContract.SaleProduct.COLUMN_PRODUCT_ID + " INTEGER NOT NULL, " +
                        DebtCollectionContract.SaleProduct.COLUMN_PRODUCT_QUANTITY + " INTEGER " +
                        ");"
                ;
        sqLiteDatabase.execSQL(SQL_CREATE_DEBT_COLLECTION_DATABASE_SALES_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + DebtCollectionContract.Client.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertClient(ContentValues clientContentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DebtCollectionContract.Client.TABLE_NAME,null,clientContentValues);
        db.close();
    }

    public void updateClient(int idClient, ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(DebtCollectionContract.Client.TABLE_NAME,
                contentValues,
                DebtCollectionContract.Client._ID + "=" + idClient,
                null);
        db.close();
    }

    public Client getClient(int idClient) {
        SQLiteDatabase db = this.getWritableDatabase();
        Client client = null;
        String query = "SELECT * FROM " +
                DebtCollectionContract.Client.TABLE_NAME +
                " WHERE " +
                DebtCollectionContract.Client._ID + " = " + idClient;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            client = new Client(
                    cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.Client._ID)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_LAST_NAME)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_PHONE_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_NOTES)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_TIN))
            );
        }
        db.close();
        return client;
    }

    public void deleteClient(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String SQLScript = "DELETE FROM " + DebtCollectionContract.Client.TABLE_NAME +
                " WHERE _id = " + id;
        db.execSQL(SQLScript);
        db.close();
    }

    public ArrayList<Client> getClients(){
        SQLiteDatabase db = this.getWritableDatabase();
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

}
