package com.glassy.salesmanager.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_DEBT_COLLECTION_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + DebtCollectionContract.Client.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
