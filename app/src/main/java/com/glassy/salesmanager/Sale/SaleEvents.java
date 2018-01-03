package com.glassy.salesmanager.Sale;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.Sale.Sale;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

interface SaleEvents {
    void loadSalesList(ArrayList<Sale> sales);
    void loadSales();
    void loadSale(Sale sale);
    void saveSaleSuccess();
    void loadProducts();
    void loadProductsSucces(ArrayList<Product> products);
    void loadClients();
    void loadClientsSuccess(ArrayList<Client> clients);
    void addNewSale(Sale newSale);
    void addNewProductSuccess();
    Context getContext();

    void deleteSaleSuccess();

    void retrieveSale(int saleId);

    void loadSaleSuccess(Sale sale);
}
