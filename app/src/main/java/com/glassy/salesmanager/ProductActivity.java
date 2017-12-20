package com.glassy.salesmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Product;
import com.glassy.salesmanager.MVP.Presenters.ProductPresenter;
import com.glassy.salesmanager.MVP.Views.ProductView;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductView{

    ProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        presenter = new ProductPresenter(this);
    }

    public void onClickbtnNewProduct(View view){
        Intent intent = new Intent(
                getApplicationContext(),
                AddProductActivity.class);
        intent.putExtra("mode","CREATE");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            Product product = data.getParcelableExtra("newProduct");
            presenter.addNewProduct(product);
        }
    }

    @Override
    public void loadProductsList(ArrayList<Product> products) {

    }

    @Override
    public void readProduct(Product product) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
