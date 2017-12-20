package com.glassy.salesmanager.Events;

import android.content.Context;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Product;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public interface ProductEvents {
    void loadProductsList(ArrayList<Product> products);
    void addNewProduct(Product newProduct);
    void addNewProductSuccess();
    Context getContext();
}
