package com.glassy.salesmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }

    public void onClickbtnNewProduct(View view){
        Intent intent = new Intent(
                getApplicationContext(),
                AddClientActivity.class);
        intent.putExtra("mode","CREATE");
        startActivityForResult(intent,1);
    }
}
