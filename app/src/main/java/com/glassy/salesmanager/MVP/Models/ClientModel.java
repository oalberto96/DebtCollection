package com.glassy.salesmanager.MVP.Models;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glassy.salesmanager.Events.UserEvents;
import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

import java.util.ArrayList;

/**
 * Created by glassy on 12/17/17.
 */

public class ClientModel {
    final protected UserEvents events;
    private SQLiteDatabase db;

    public ClientModel(UserEvents events) {
        this.events = events;

        DebtCollectionDBHelper dbHelper = new DebtCollectionDBHelper(events.getAppContext());
        db = dbHelper.getWritableDatabase();

    }

    public void loadClients(){
        events.loadListSeller(getClients());
    }

    public void createClient(Client newClient){
        String dbInsert = "INSERT INTO " + DebtCollectionContract.Client.TABLE_NAME + " (" +
                DebtCollectionContract.Client.COLUMN_FIRST_NAME + ", " +
                DebtCollectionContract.Client.COLUMN_LAST_NAME + ", " +
                DebtCollectionContract.Client.COLUMN_ADDRESS + ", " +
                DebtCollectionContract.Client.COLUMN_PHONE_NUMBER + ", " +
                DebtCollectionContract.Client.COLUMN_NOTES + ", " +
                DebtCollectionContract.Client.COLUMN_TIN +
                ") VALUES (" + "\"" +
                newClient.getFirst_name() + "\", \"" +
                newClient.getLast_name() + "\", \"" +
                newClient.getAddress() + "\", \"" +
                newClient.getPhoneNumber() + "\", \"" +
                newClient.getNotes() + "\", \"" +
                newClient.getTin() +
                "\");";
        db.execSQL(dbInsert);
        events.addClientEvent();
    }

    public ArrayList<Client> getClients(){
        ArrayList<Client> clients = new ArrayList<Client>();
        String query = "SELECT * FROM " +
                DebtCollectionContract.Client.TABLE_NAME+";";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                clients.add(new Client(
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_FIRST_NAME)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_LAST_NAME)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_PHONE_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_NOTES)),
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Client.COLUMN_TIN))
                ));
            }
        }
        return clients;
    }

}
