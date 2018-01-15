package com.glassy.salesmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.glassy.salesmanager.Client.Client;

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
}
