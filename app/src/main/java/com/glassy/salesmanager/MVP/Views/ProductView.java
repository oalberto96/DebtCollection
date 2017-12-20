package com.glassy.salesmanager.MVP.Views;

import android.content.Context;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Product;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public interface ProductView {
    void loadProductsList(ArrayList<Product> products);
    void readProduct(Product product);
    Context getContext();
}
