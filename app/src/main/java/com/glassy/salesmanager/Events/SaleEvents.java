package com.glassy.salesmanager.Events;

import android.content.Context;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Product;
import com.glassy.salesmanager.MVP.Models.Sale;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public interface SaleEvents {
    void loadProductsList(ArrayList<Sale> sales);
    void loadProducts();
    void loadSale(Sale sale);
    void loadClients();
    void loadClientsSuccess(ArrayList<Client> clients);
    void addNewSale(Sale newSale);
    void addNewProductSuccess();
    Context getContext();
}
