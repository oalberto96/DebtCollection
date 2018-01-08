package com.glassy.salesmanager.Product;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.glassy.salesmanager.R;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity implements ProductView{
    Product product;

    ProductPresenter presenter;

    Button btnAddNewProduct;
    Button btnUpdateProduct;
    EditText productName;
    EditText productPrice;
    EditText productColor;
    EditText productSize;
    EditText productMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productName = (EditText) findViewById(R.id.et_product_name);
        productPrice = (EditText) findViewById(R.id.et_product_price);
        productColor = (EditText) findViewById(R.id.et_product_color);
        productSize = (EditText) findViewById(R.id.et_product_size);
        productMaterial = (EditText) findViewById(R.id.et_product_material);
        btnAddNewProduct = (Button) findViewById(R.id.btn_new_product);
        btnUpdateProduct = (Button) findViewById(R.id.btn_update_product);

        presenter = new ProductPresenter(this);
        getExtraMessages();
    }

    private void getExtraMessages(){
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        if (mode != null){
            switch (mode){
                case "CREATE":
                    btnUpdateProduct.setVisibility(View.GONE);
                    break;
                case "UPDATE":
                    btnAddNewProduct.setVisibility(View.GONE);
                    presenter.getProduct(intent.getIntExtra("productId",0));
            }
        }
    }

    public void onClickbtnAddNewProduct(View view){
        Product product = new Product(
                productName.getText().toString(),
                Float.parseFloat(productPrice.getText().toString()),
                productColor.getText().toString(),
                productSize.getText().toString(),
                productMaterial.getText().toString()
        );
        Intent intent = new Intent();
        intent.putExtra("newProduct",(Parcelable) product);
        setResult(RESULT_OK,intent);
        onBackPressed();
    }

    public void onClickbtnUpdateProduct(View view){
        product = new Product(
                product.getId(),
                productName.getText().toString(),
                Float.parseFloat(productPrice.getText().toString()),
                productColor.getText().toString(),
                productSize.getText().toString(),
                productMaterial.getText().toString()
        );
        presenter.updateProduct(product);
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
        Intent intent = new Intent();
        intent.putExtra("PRODUCT",(Parcelable) product);
        setResult(RESULT_OK,intent);
        onBackPressed();
    }

    private void fillForm(Product product) {
        this.product = product;

        productName.setText(product.getName());
        productPrice.setText(String.valueOf(product.getPrice()));
        productColor.setText(product.getColor());
        productSize.setText(product.getSize());
        productMaterial.setText(product.getMaterial());
    }


}
