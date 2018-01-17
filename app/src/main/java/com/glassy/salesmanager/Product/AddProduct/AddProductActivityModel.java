package com.glassy.salesmanager.Product.AddProduct;

import android.content.ContentValues;

import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.data.DebtCollectionContract;
import com.glassy.salesmanager.data.DebtCollectionDBHelper;

/**
 * Created by glassy on 1/16/18.
 */

public class AddProductActivityModel {
    private IAddProductPresenter presenter;
    private DebtCollectionDBHelper dbHelper;

    public AddProductActivityModel(IAddProductPresenter presenter){
        this.presenter = presenter;
        dbHelper = new DebtCollectionDBHelper(this.presenter.getActivityContext());
    }

    public void insertProduct(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DebtCollectionContract.Product.COLUMN_NAME,product.getName());
        contentValues.put(DebtCollectionContract.Product.COLUMN_PRICE,product.getPrice());
        contentValues.put(DebtCollectionContract.Product.COLUMN_COLOR,product.getColor());
        contentValues.put(DebtCollectionContract.Product.COLUMN_SIZE,product.getSize());
        contentValues.put(DebtCollectionContract.Product.COLUMN_MATERIAL,product.getMaterial());
        dbHelper.insertProduct(contentValues);
        presenter.insertProductSuccess();
    }
}
