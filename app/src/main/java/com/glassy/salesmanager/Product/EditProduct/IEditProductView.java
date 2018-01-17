package com.glassy.salesmanager.Product.EditProduct;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

/**
 * Created by glassy on 1/16/18.
 */

interface IEditProductView {
    void getExtraMessages();
    void fillForm(Product product);
    Context getActivityContext();
    void updateProductSuccess();
}
