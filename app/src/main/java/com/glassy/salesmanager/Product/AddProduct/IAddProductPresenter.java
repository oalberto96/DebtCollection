package com.glassy.salesmanager.Product.AddProduct;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

/**
 * Created by glassy on 1/16/18.
 */

interface IAddProductPresenter {

    Context getActivityContext();
    void insertProduct(Product product);
    void insertProductSuccess();
}
