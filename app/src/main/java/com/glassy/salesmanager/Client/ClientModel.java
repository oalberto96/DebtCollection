package com.glassy.salesmanager.Client;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

import java.util.ArrayList;

/**
 * Created by glassy on 12/17/17.
 */

public class ClientModel {
    final protected UserEvents events;
    private SQLiteDatabase db;
    DebtCollectionDBHelper dbHelper;

    public ClientModel(UserEvents events) {
        this.events = events;

        dbHelper = new DebtCollectionDBHelper(events.getAppContext());


    }

    public void loadClients(){
        events.loadListSeller(getClients());
    }

    public void createClient(Client newClient){
        db = dbHelper.getWritableDatabase();
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
        db.close();
        events.addClientEvent();

    }

    public void readClient(int id){
        db = dbHelper.getWritableDatabase();

        Client client;
        String query = "SELECT * FROM " +
                DebtCollectionContract.Client.TABLE_NAME +
                " WHERE "+
                DebtCollectionContract.Client._ID+" = " + id;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount() > 0){
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
            db.close();
            events.readClientSuccessEvent(client);
        }
        db.close();
    }

    public void deleteClient(int id){
        db = dbHelper.getWritableDatabase();
        String SQLScript = "DELETE FROM " + DebtCollectionContract.Client.TABLE_NAME +
                " WHERE _id = " + id;
        db.execSQL(SQLScript);
        db.close();
        events.addClientEvent();
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

    public void updateClient(Client client) {
        db = dbHelper.getWritableDatabase();
        String dbInsert = "UPDATE " + DebtCollectionContract.Client.TABLE_NAME + " SET " +
                DebtCollectionContract.Client.COLUMN_FIRST_NAME + " = " +
                "\"" + client.getFirst_name() + "\", " +
                DebtCollectionContract.Client.COLUMN_LAST_NAME + " = " +
                "\"" + client.getLast_name() + "\", " +
                DebtCollectionContract.Client.COLUMN_ADDRESS + " = " +
                "\"" + client.getAddress()+ "\", " +
                DebtCollectionContract.Client.COLUMN_PHONE_NUMBER + " = " +
                "\"" + client.getPhoneNumber()+ "\", " +
                DebtCollectionContract.Client.COLUMN_NOTES + " = " +
                "\"" + client.getNotes() + "\", " +
                DebtCollectionContract.Client.COLUMN_TIN + " = " +
                "\"" + client.getTin() + "\" " +
                "WHERE " + DebtCollectionContract.Client._ID + " = " +
                client.getId() + ";";
        db.execSQL(dbInsert);
        db.close();
        events.updateClientSuccessEvent(client.getId());
    }
}
