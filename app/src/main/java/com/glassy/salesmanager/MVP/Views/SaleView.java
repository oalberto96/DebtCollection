package com.glassy.salesmanager.MVP.Views;

import android.content.Context;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Product;
import com.glassy.salesmanager.MVP.Models.Sale;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public interface SaleView {
    void loadSaleList(ArrayList<Sale> sales);
    void readSale(Sale sale);
    void printTotal(float total);
    void productAdded(ArrayList<Product> products);
    void loadClientsSuccess(ArrayList<Client> clients);
    void loadProductsSuccess(ArrayList<Product> products);
    Context getContext();
}
