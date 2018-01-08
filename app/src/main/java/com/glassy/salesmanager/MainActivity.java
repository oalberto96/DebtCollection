package com.glassy.salesmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.glassy.salesmanager.Client.ClientActivity;
import com.glassy.salesmanager.Product.ProductActivity;
import com.glassy.salesmanager.Sale.SaleActivity;
import com.glassy.salesmanager.Stats.StatsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickbtnClient(View view){
        startActivity(new Intent(
                this,
                ClientActivity.class
        ));
    }

    public void onClickbtnProduct(View view){
        startActivity(new Intent(
                this,
                ProductActivity.class
        ));
    }

    public void onClickbtnSale(View view){
        startActivity(new Intent(
                this,
                SaleActivity.class
        ));
    }

    public void onClickbtnStats(View view){
        startActivity(new Intent(
                this,
                StatsActivity.class
        ));
    }


}
