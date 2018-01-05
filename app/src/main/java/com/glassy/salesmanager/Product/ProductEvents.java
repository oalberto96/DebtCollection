package com.glassy.salesmanager.Product;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

import java.util.ArrayList;

/**
 * Created by glassy on 12/19/17.
 */

public interface ProductEvents {
    void loadProductsList(ArrayList<Product> products);
    void addNewProduct(Product newProduct);
    void addNewProductSuccess();
    Context getContext();

    void loadProductSuccess(Product product);

    void updateProduct(Product product);

    void updateProductSuccess();
}
