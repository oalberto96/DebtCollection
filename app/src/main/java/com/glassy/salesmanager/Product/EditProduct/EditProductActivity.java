package com.glassy.salesmanager.Product.EditProduct;

import android.content.Context;
import android.content.Intent;
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
import com.glassy.salesmanager.R;

public class EditProductActivity extends AppCompatActivity implements IEditProductView {

    public EditProductActivityPresenter presenter;

    Button btnAddNewProduct;
    EditText productName;
    EditText productPrice;
    EditText productColor;
    EditText productSize;
    EditText productMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

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

        presenter = new EditProductActivityPresenter(this);
        getExtraMessages();
    }


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
                onClickbtnAddNewProduct();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getExtraMessages() {
        Intent intent = getIntent();
        presenter.getProduct(intent.getIntExtra("productId", 0));
    }


    @Override
    public void fillForm(Product product) {
        productName.setText(product.getName());
        productPrice.setText(String.valueOf(product.getPrice()));
        productColor.setText(product.getColor());
        productSize.setText(product.getSize());
        productMaterial.setText(product.getMaterial());
    }

    public void onClickbtnAddNewProduct(){
        presenter.product.setName(productName.getText().toString());
        presenter.product.setPrice(Float.parseFloat(productPrice.getText().toString()));
        presenter.product.setColor(productColor.getText().toString());
        presenter.product.setSize(productSize.getText().toString());
        presenter.product.setMaterial(productMaterial.getText().toString());
        presenter.updateProduct();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void updateProductSuccess() {
        Intent intent = getIntent();
        setResult(RESULT_OK,intent);
        onBackPressed();
    }
}
