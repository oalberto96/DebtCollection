package com.glassy.salesmanager.Product;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.glassy.salesmanager.R;

public class AddProductActivity extends AppCompatActivity {
    Button btnAddNewProduct;
    EditText productName;
    EditText productPrize;
    EditText productColor;
    EditText productSize;
    EditText productMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productName = (EditText) findViewById(R.id.et_product_name);
        productPrize = (EditText) findViewById(R.id.et_product_price);
        productColor = (EditText) findViewById(R.id.et_product_color);
        productSize = (EditText) findViewById(R.id.et_product_size);
        productMaterial = (EditText) findViewById(R.id.et_product_material);
        btnAddNewProduct = (Button) findViewById(R.id.btn_new_product);
    }

    private void getExtraMessages(){
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        if (mode != null){
            switch (mode){
                case "CREATE":
                    break;
            }
        }
    }

    public void onClickbtnAddNewProduct(View view){
        Product product = new Product(
                productName.getText().toString(),
                Float.parseFloat(productPrize.getText().toString()),
                productColor.getText().toString(),
                productSize.getText().toString(),
                productMaterial.getText().toString()
        );
        Intent intent = new Intent();
        intent.putExtra("newProduct",(Parcelable) product);
        setResult(RESULT_OK,intent);
        onBackPressed();
    }

}
