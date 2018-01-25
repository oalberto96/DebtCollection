package com.glassy.salesmanager.Sale.AddSale;

import android.content.Context;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;

import java.util.ArrayList;

/**
 * Created by glassy on 1/18/18.
 */

public interface IAddSaleView {
    void printTotal(float total);
    Context getActivityContext();

    void populateClientList(ArrayList<Client> clients);

    void populateProductList(ArrayList<Product> products);

    void productAddedToSale();

    void saveSaleSuccess();

    void refreshProductList();
}
