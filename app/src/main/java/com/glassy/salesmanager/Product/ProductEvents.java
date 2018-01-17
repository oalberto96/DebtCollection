package com.glassy.salesmanager.Product;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public interface ProductEvents {

    void loadProducts();

    void loadProductsListSuccess(ArrayList<Product> products);

    void addItemToList(int id);

    void deleteItemFromList();

    void loadProductList();

    void cleanList();

    void deleteProductSuccess();

    Context getAppContext();
}
