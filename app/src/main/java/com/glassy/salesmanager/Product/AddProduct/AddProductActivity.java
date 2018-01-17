package com.glassy.salesmanager.Product.AddProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.Product.ProductPresenter;
import com.glassy.salesmanager.Product.ProductView;
import com.glassy.salesmanager.R;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity implements IAddProductView{

    Product product;
    public AddProductActivityPresenter presenter;

    Button btnAddNewProduct;
    EditText productName;
    EditText productPrice;
    EditText productColor;
    EditText productSize;
    EditText productMaterial;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.done_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_menu_done:
                //onClickbtnAddClient();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar actionbar = (Toolbar) findViewById(R.id.my_action_bar);
        setSupportActionBar(actionbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productName = (EditText) findViewById(R.id.et_product_name);
        productPrice = (EditText) findViewById(R.id.et_product_price);
        productColor = (EditText) findViewById(R.id.et_product_color);
        productSize = (EditText) findViewById(R.id.et_product_size);
        productMaterial = (EditText) findViewById(R.id.et_product_material);
        btnAddNewProduct = (Button) findViewById(R.id.btn_new_product);

        presenter = new AddProductActivityPresenter(this);
    }

    public void onClickbtnAddNewProduct(View view){
        Product product = new Product(
                productName.getText().toString(),
                Float.parseFloat(productPrice.getText().toString()),
                productColor.getText().toString(),
                productSize.getText().toString(),
                productMaterial.getText().toString()
        );
        presenter.insertProduct(product);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void finishActivitySuccess() {
        onBackPressed();
    }
}
