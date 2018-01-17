package com.glassy.salesmanager.Product.EditProduct;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

/**
 * Created by glassy on 1/16/18.
 */

interface IEditProductPresenter {

    Context getActivityContext();
    void getProduct(int productId);
    void getProductSuccess(Product product);
    void updateProduct();
    void updateProductSuccess();
}
