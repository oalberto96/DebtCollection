package com.glassy.salesmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.glassy.salesmanager.MVP.Models.Product;
import com.glassy.salesmanager.MVP.Models.Sale;
import com.glassy.salesmanager.MVP.Presenters.ProductPresenter;
import com.glassy.salesmanager.MVP.Presenters.SalePresenter;
import com.glassy.salesmanager.MVP.Views.ProductView;
import com.glassy.salesmanager.MVP.Views.SaleView;
import com.glassy.salesmanager.UI.ProductAdapter;
import com.glassy.salesmanager.UI.SaleAdapter;

import java.util.ArrayList;

public class SaleActivity extends AppCompatActivity implements SaleView, SaleAdapter.ListItemClickListener{
    SaleAdapter saleAdapter;
    RecyclerView saleList;

    SalePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        presenter = new SalePresenter(this);
    }

    public void onClickbtnNewSale(View view){
        Intent intent = new Intent(
                getApplicationContext(),
                AddSaleActivity.class);
        intent.putExtra("mode","CREATE");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            Product product = data.getParcelableExtra("newProduct");
            //presenter.addNewProduct(product);
        }
    }


    @Override
    public void loadSaleList(ArrayList<Sale> sales) {
        saleList = (RecyclerView) findViewById(R.id.rv_sales);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        saleList.setLayoutManager(layoutManager);
        saleList.setHasFixedSize(true);
        saleAdapter = new SaleAdapter(sales, this);
        saleList.setAdapter(saleAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                //presenter.deleteClient(id);
            }
        }).attachToRecyclerView(saleList);
    }

    @Override
    public void readSale(Sale sale) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClickListener(int id) {

    }
}
