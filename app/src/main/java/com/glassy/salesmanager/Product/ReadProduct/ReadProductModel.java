package com.glassy.salesmanager.Product.ReadProduct;

import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

/**
 * Created by glassy on 1/16/18.
 */

class ReadProductModel {
    IReadProductPresenter presenter;
    DebtCollectionDBHelper dbHelper;

    public ReadProductModel(IReadProductPresenter readProductPresenter) {
        this.presenter = readProductPresenter;
        dbHelper = new DebtCollectionDBHelper(this.presenter.getActivityContext());
    }

    public void getProduct(int productId) {
        Product product = dbHelper.getProduct(productId);
        presenter.getProductSuccess(product);
    }
}
