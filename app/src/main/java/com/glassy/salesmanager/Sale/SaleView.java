package com.glassy.salesmanager.Sale;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.Sale.Sale;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public interface SaleView {
    void loadSaleList(ArrayList<Sale> sales);
    void readSale(Sale sale);
    void saveSaleSuccess();
    void printTotal(float total);
    void productAdded(ArrayList<Product> products);
    void loadClientsSuccess(ArrayList<Client> clients);
    void loadProductsSuccess(ArrayList<Product> products);
    void saveSaleFail(String message);
    Context getContext();
}
