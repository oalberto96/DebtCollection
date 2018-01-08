package com.glassy.salesmanager.Product;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.glassy.salesmanager.R;

import java.util.ArrayList;

public class ReadProductActivity extends AppCompatActivity implements ProductView {

    ProductPresenter presenter;
    Product product;

    TextView tvProductName;
    TextView tvProductPrice;
    TextView tvProductColor;
    TextView tvProductSize;
    TextView tvProductMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_product);

        tvProductName = (TextView) findViewById(R.id.tv_product_name);
        tvProductPrice = (TextView) findViewById(R.id.tv_product_price);
        tvProductColor = (TextView) findViewById(R.id.tv_product_color);
        tvProductSize = (TextView) findViewById(R.id.tv_product_size);
        tvProductMaterial = (TextView) findViewById(R.id.tv_product_material);

        presenter = new ProductPresenter(this);

        getExtraMessages();
    }

    private void getExtraMessages(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("productId",0);
        presenter.getProduct(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            Product product = data.getParcelableExtra("PRODUCT");
            fillForm(product);
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

    @Override
    public void loadProduct(Product product) {
        fillForm(product);
    }

    @Override
    public void updateProductSuccess() {

    }

    private void fillForm(Product product) {
        this.product = product;
        tvProductName.setText(product.getName());
        tvProductPrice.setText(String.valueOf(product.getPrice()));
        tvProductColor.setText(product.getColor());
        tvProductSize.setText(product.getSize());
        tvProductMaterial.setText(product.getMaterial());
    }

    public void onClickbtnEditProduct(View view){
        Intent intent = new Intent(this, AddProductActivity.class);
        intent.putExtra("mode", "UPDATE");
        intent.putExtra("productId", product.getId());
        startActivityForResult(intent,1);
    }
}
