package com.glassy.salesmanager.Product;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.glassy.salesmanager.Product.AddProduct.AddProductActivity;
import com.glassy.salesmanager.Product.ReadProduct.ReadProductActivity;
import com.glassy.salesmanager.R;
import com.glassy.salesmanager.UI.ProductAdapter;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements ProductView, ProductAdapter.ListItemClickListener{
    ProductAdapter productAdapter;
    ProductEvents presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        presenter = new ProductPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK){
            presenter.loadProducts();
        }
    }

    public void onClickbtnNewProduct(View view){
        Intent intent = new Intent(
                getApplicationContext(),
                AddProductActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void initRecyclerView(ArrayList<Product> products) {
        RecyclerView productList = (RecyclerView) findViewById(R.id.rv_products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        productList.setHasFixedSize(true);
        productAdapter = new ProductAdapter(products, this);
        productList.setAdapter(productAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = (int) viewHolder.itemView.getTag();
                showDeleteSnackbar(id);
            }
        }).attachToRecyclerView(productList);
    }

    @Override
    public void onItemClickListener(int id) {
        Intent intent = new Intent(this,ReadProductActivity.class);
        intent.putExtra("productId",id);
        startActivityForResult(intent,3);
    }

    @Override
    public void refreshRecyclerView() {
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getAppContext() {
        return this;
    }


    private void showDeleteSnackbar(int id) {
        presenter.addItemToList(id);
        Snackbar.make(findViewById(R.id.product_activity_layout),
                R.string.client_deleted,
                Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.loadProductList();
                        presenter.deleteItemFromList();
                    }
                }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                presenter.cleanList();
            }
        }).show();
    }




}
