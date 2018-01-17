package com.glassy.salesmanager.Product.ReadProduct;

import android.content.Context;

import com.glassy.salesmanager.Product.Product;

/**
 * Created by glassy on 1/16/18.
 */

interface IReadProductView {
    Context getActivityContext();

    void fillForm(Product product);
}
