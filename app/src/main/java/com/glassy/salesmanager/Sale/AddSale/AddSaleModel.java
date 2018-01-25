package com.glassy.salesmanager.Sale.AddSale;

import android.content.ContentValues;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.Sale.Sale;
import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by glassy on 1/18/18.
 */

class AddSaleModel {
    IAddSalePresenter presenter;
    DebtCollectionDBHelper dbHelper;

    public AddSaleModel(IAddSalePresenter presenter){
        this.presenter = presenter;
        dbHelper = new DebtCollectionDBHelper(presenter.getActivityContext());
    }

    public void getProducts(){
        ArrayList<Product> products = dbHelper.getProducts();
        presenter.loadProductsSuccess(products);
    }

    public void getClients(){
        ArrayList<Client> clients = dbHelper.getClients();
        presenter.loadClientsSuccess(clients);
    }

    public void getClient(int clientId){
        Client client = dbHelper.getClient(clientId);
        presenter.loadClientSuccess(client);
    }

    public void getProduct(int productId){
        Product product = dbHelper.getProduct(productId);
        presenter.loadProductSuccess(product);
    }


    public void insertSale(Sale sale) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DebtCollectionContract.Sale.COLUMN_NAME,sale.getName());
        contentValues.put(DebtCollectionContract.Sale.COLUMN_DATE, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sale.getDateSale()));
        contentValues.put(DebtCollectionContract.Sale.CLIENT_ID, sale.getClient().getId());
        dbHelper.insertSale(contentValues, sale.getProducts(), sale.getProduct_quantity());
        presenter.saveSaleSuccess();
    }
}
