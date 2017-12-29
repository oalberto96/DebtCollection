package com.glassy.salesmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.glassy.salesmanager.MVP.Models.Client;
import com.glassy.salesmanager.MVP.Models.Product;
import com.glassy.salesmanager.MVP.Models.Sale;
import com.glassy.salesmanager.MVP.Presenters.SalePresenter;
import com.glassy.salesmanager.MVP.Views.SaleView;
import com.glassy.salesmanager.UI.ProductAdapter;
import com.glassy.salesmanager.UI.SaleAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class AddSaleActivity extends AppCompatActivity implements SaleView, ProductAdapter.ListItemClickListener{

    SalePresenter presenter;
    RecyclerView productList;
    ProductAdapter productAdapter;
    Spinner s_clientList;
    TextView tv_total;

    HashMap<Integer, String> s_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);
        presenter = new SalePresenter(this);
        presenter.loadClients();
        tv_total = (TextView) findViewById(R.id.tv_total);
        initRecyclerView(presenter.getProducts());
    }


    @Override
    public void loadSaleList(ArrayList<Sale> sales) {
    }

    @Override
    public void readSale(Sale sale) {

    }

    @Override
    public void printTotal(float total) {
        tv_total.setText(String.valueOf(total));
    }

    public void onClickbtnAddProduct(View view){
        presenter.loadProducts();
    }

    @Override
    public void loadClientsSuccess(ArrayList<Client> clients) {
        populateClientList(clients);
    }

    @Override
    public void loadProductsSuccess(final ArrayList<Product> products) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayList<String> nameProduct = new ArrayList<>();
        for(Product product: products){
            nameProduct.add( product.getName());
        }
        builder.setTitle(getResources().getString(R.string.select_product));
        builder.setItems(nameProduct.toArray(new CharSequence[nameProduct.size()]),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                Product product = products.get(position);
                presenter.addProduct(product,getQuantity());
                //Toast.makeText(getContext(),"" + products.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public void initRecyclerView(ArrayList<Product> products){
        productList = (RecyclerView) findViewById(R.id.rv_sales_products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        productList.setHasFixedSize(true);
        productAdapter = new ProductAdapter(products, this);
        productList.setAdapter(productAdapter);
    }

    @Override
    public void productAdded(ArrayList<Product> products){
        productAdapter.notifyDataSetChanged();
        //initRecyclerView(products);
    }


    public void populateClientList(ArrayList<Client> clients){
        ArrayList<String> s_clients = new ArrayList<String>();
        s_map = new HashMap<Integer, String>();
        int i=0;
        for (Client client: clients){
            s_map.put(client.getId(),client.getFirst_name());
            s_clients.add(client.getFirst_name());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, s_clients);
        s_clientList = (Spinner)findViewById(R.id.s_client_list);
        s_clientList.setAdapter(adapter);
    }

    public ArrayList<Integer> getQuantity(){
        ArrayList<Integer> quantity = new ArrayList<>();
        int countItems = productList.getLayoutManager().getChildCount();
        for (int i = 0; i < countItems; i++ ){
            FrameLayout frameLayout = (FrameLayout) productList.getLayoutManager().findViewByPosition(i);
            EditText editText = (EditText) frameLayout.getChildAt(1);
            try {
                quantity.add(Integer.parseInt(editText.getText().toString()));
            }
            catch(Exception e){
                quantity.add(1);
            }
        }
        return quantity;
    }

    public void onClickbtnAddSale(View view){
        for(Integer i :presenter.getSale().getProduct_quantity()){
            Toast.makeText(this, "" + i, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClickListener(int id) {

    }
}
