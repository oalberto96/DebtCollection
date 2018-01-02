package com.glassy.salesmanager.Sale;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.glassy.salesmanager.Sale.SaleEvents;
import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

import java.sql.Timestamp;
import java.util.ArrayList;

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

    public void loadSaleList() {
        events.loadSalesList(getSales());
    }

    public void loadProducts() {
        events.loadProductsSucces(getProducts());
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " +
                DebtCollectionContract.Product.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
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

    public void loadClients() {
        events.loadClientsSuccess(getClients());
    }

    public ArrayList<Client> getClients() {
        db = dbHelper.getWritableDatabase();
        ArrayList<Client> clients = new ArrayList<Client>();
        String query = "SELECT * FROM " +
                DebtCollectionContract.Client.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
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

    public void saveSale(Sale sale) {
        sale.setId(saveSaleTable(sale));
        saveSaleProductTable(sale);
        events.saveSaleSuccess();
    }

    public Client getClient(int id) {
        db = dbHelper.getWritableDatabase();
        Client client = new Client("", "");
        String query = "SELECT * FROM " +
                DebtCollectionContract.Client.TABLE_NAME +
                " WHERE " +
                DebtCollectionContract.Client._ID + " = " + id;
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

    private void saveSaleProductTable(Sale sale) {
        db = dbHelper.getWritableDatabase();
        for (int position = 0; position < sale.getProducts().size(); position++) {
            String dbInsert = "INSERT INTO " + DebtCollectionContract.SaleProduct.TABLE_NAME + " (" +
                    DebtCollectionContract.SaleProduct.COLUMN_SALE_ID + ", " +
                    DebtCollectionContract.SaleProduct.COLUMN_PRODUCT_ID + ", " +
                    DebtCollectionContract.SaleProduct.COLUMN_PRODUCT_QUANTITY +
                    ") VALUES (" + "\"" +
                    sale.getId() + "\", \"" +
                    sale.getProducts().get(position).getId() + "\", \"" +
                    sale.getProduct_quantity().get(position) +
                    "\");";
            db.execSQL(dbInsert);
        }
        db.close();
    }

    private int saveSaleTable(Sale sale) {
        db = dbHelper.getWritableDatabase();
        String dbInsert = "INSERT INTO " + DebtCollectionContract.Sale.TABLE_NAME + " (" +
                DebtCollectionContract.Sale.COLUMN_NAME + ", " +
                DebtCollectionContract.Sale.CLIENT_ID +
                ") VALUES (" + "\"" +
                sale.getName() + "\", \"" +
                sale.getClient().getId() +
                "\");";
        db.execSQL(dbInsert);
        db.close();
        return getLastSaleID();
    }

    private int getLastSaleID() {
        db = dbHelper.getWritableDatabase();
        int id = 0;
        String query = "SELECT * FROM " +
                DebtCollectionContract.Sale.TABLE_NAME +
                " ORDER BY " +
                DebtCollectionContract.Sale._ID +
                " DESC LIMIT 1;";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            id = cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.Sale._ID));
        }
        db.close();
        return id;
    }


    public void saleProduct(Sale sale) {
        //Sql
    }

    public void deleteClient(int id) {
        //sql
    }

    public Product getProduct(int id){
        db = dbHelper.getWritableDatabase();
        Product product = null;
        String query = "SELECT * FROM " +
                DebtCollectionContract.Product.TABLE_NAME +
                " WHERE "+
                DebtCollectionContract.Product._ID+" = " + id;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.getCount() > 0){
            cursor.moveToNext();
             product = new Product(
                    cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.Product._ID)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_NAME)),
                    cursor.getFloat(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_PRICE)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_COLOR)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_SIZE)),
                    cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Product.COLUMN_MATERIAL))
            );
        }
        db.close();
        return  product;
    }

        public ArrayList<Sale> getSales() {
        ArrayList<Sale> sales = new ArrayList<Sale>();
        db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " +
                DebtCollectionContract.Sale.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int saleId = cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.Sale._ID));
                ArrayList<Product> products = new ArrayList<>();
                ArrayList<Integer> quantity = new ArrayList<>();
                for (Sale.SaleProduct sale: retrieveSaleProduct(saleId)){
                    products.add(getProduct(sale.productId));
                    quantity.add(sale.productQuantity);
                }
                sales.add(new Sale(
                        saleId,
                        cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Sale.COLUMN_NAME)),
                        getClient(cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.Sale.CLIENT_ID))),
                        products,
                        quantity,
                        Timestamp.valueOf(cursor.getString(cursor.getColumnIndex(DebtCollectionContract.Sale.COLUMN_DATE)))
                ));
            }
        }
        db.close();
        return sales;
    }

    public ArrayList<Sale.SaleProduct> retrieveSaleProduct(int saleIndex) {
        ArrayList<Sale.SaleProduct> saleProducts = new ArrayList<>();
        db = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " +
                DebtCollectionContract.SaleProduct.TABLE_NAME +
                " WHERE " +
                DebtCollectionContract.SaleProduct.COLUMN_SALE_ID + " = " + saleIndex;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            while(cursor.moveToNext()){
                saleProducts.add(new Sale.SaleProduct(
                        cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.SaleProduct.COLUMN_SALE_ID)),
                        cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.SaleProduct.COLUMN_PRODUCT_ID)),
                        cursor.getInt(cursor.getColumnIndex(DebtCollectionContract.SaleProduct.COLUMN_PRODUCT_QUANTITY))
                ));
            }
            db.close();
        }
        db.close();
        return saleProducts;
    }

    public void deleteSale(int id) {
        db = dbHelper.getWritableDatabase();
        String SQLScript = "DELETE FROM " + DebtCollectionContract.Sale.TABLE_NAME +
                " WHERE _id = " + id;
        db.execSQL(SQLScript);
        db.close();
        events.deleteSaleSuccess();
    }
}
