package com.glassy.salesmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.glassy.salesmanager.MVP.Models.Sale;
import com.glassy.salesmanager.MVP.Presenters.SalePresenter;
import com.glassy.salesmanager.MVP.Views.SaleView;

import java.util.ArrayList;

public class AddSaleActivity extends AppCompatActivity implements SaleView{

    SalePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);
        presenter = new SalePresenter(this);

    }


    @Override
    public void loadSaleList(ArrayList<Sale> sales) {

    }

    @Override
    public void readSale(Sale sale) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
