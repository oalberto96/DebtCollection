package com.glassy.salesmanager.Product.ReadProduct;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.glassy.salesmanager.Product.AddProduct.AddProductActivity;
import com.glassy.salesmanager.Product.EditProduct.EditProductActivity;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.Product.ProductPresenter;
import com.glassy.salesmanager.Product.ProductView;
import com.glassy.salesmanager.R;

import java.util.ArrayList;

public class ReadProductActivity extends AppCompatActivity implements IReadProductView {

    IReadProductPresenter presenter;
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

        presenter = new ReadProductPresenter(this);

        getProductData();
    }

    private void getProductData(){
        Intent intent = getIntent();
        int productId = intent.getIntExtra("productId",0);
        presenter.getProduct(productId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            getProductData();
        }
    }

    @Override
    public void fillForm(Product product) {
        this.product = product;
        tvProductName.setText(product.getName());
        tvProductPrice.setText(String.valueOf(product.getPrice()));
        tvProductColor.setText(product.getColor());
        tvProductSize.setText(product.getSize());
        tvProductMaterial.setText(product.getMaterial());
    }

    public void onClickbtnEditProduct(View view){
        Intent intent = new Intent(this, EditProductActivity.class);
        intent.putExtra("productId", product.getId());
        startActivityForResult(intent,1);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
