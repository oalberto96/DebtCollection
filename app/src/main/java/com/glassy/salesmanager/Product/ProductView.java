package com.glassy.salesmanager.Product;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public interface ProductView {
    void loadProductsList(ArrayList<Product> products);
    void readProduct(Product product);
    Context getContext();
}
