package com.glassy.salesmanager.Sale.AddSale;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.Sale.Sale;

import java.util.ArrayList;

/**
 * Created by glassy on 1/18/18.
 */

interface IAddSalePresenter {
    Context getActivityContext();

    void getProducts();

    void getClients();

    void loadProductsSuccess(ArrayList<Product> products);
    void loadClientsSuccess(ArrayList<Client> clients);
    void loadClientSuccess(Client client);
    void loadProductSuccess(Product product);

    Sale getSale();

    void addProduct(Product product);

    void getSaleTotal();

    void saveSale();

    void saveSaleSuccess();
}
