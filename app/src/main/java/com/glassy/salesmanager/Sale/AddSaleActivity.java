package com.glassy.salesmanager.Sale;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.R;
import com.glassy.salesmanager.UI.ProductAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AddSaleActivity extends AppCompatActivity implements SaleView, ProductAdapter.ListItemClickListener{

    SalePresenter presenter;
    RecyclerView productList;
    ProductAdapter productAdapter;
    Spinner s_clientList;
    TextView tv_total;
    TextView tv_saleClient;
    EditText et_saleName;
    Button btn_addSale;

    HashMap<Integer, String> s_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);
        presenter = new SalePresenter(this);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_saleClient = (TextView) findViewById(R.id.tv_sale_client);
        et_saleName = (EditText) findViewById(R.id.et_sale_name);
        btn_addSale = (Button) findViewById(R.id.btn_add_sale);
        getExtraMessages();
        initRecyclerView(presenter.getProducts());
    }

    protected void getExtraMessages(){
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        if (mode != null){
            switch (mode){
                case "CREATE":
                    break;
                case "UPDATE":
                    btn_addSale.setVisibility(View.INVISIBLE);
                    presenter.retrieveSale(intent.getIntExtra("saleId",0));
            }
        }
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this){
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                changedTextView();
            }
        };
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

    public void changedTextView() {
        ArrayList<Integer> quantity = new ArrayList<>();
        int countItems = productList.getLayoutManager().getChildCount();
        if (countItems > 0 ){
            LinearLayout linearLayout = (LinearLayout) productList.getLayoutManager().findViewByPosition(countItems - 1);
            EditText editText = (EditText) linearLayout.getChildAt(1);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    presenter.getTotal(getQuantity());
                }
            });
        }
    }


    public void populateClientList(final ArrayList<Client> clients){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayList<String> nameProduct = new ArrayList<>();
        for(Client client: clients){
            nameProduct.add( client.getFullName());
        }
        builder.setTitle(getResources().getString(R.string.select_client));
        builder.setItems(nameProduct.toArray(new CharSequence[nameProduct.size()]),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                Client client = clients.get(position);
                tv_saleClient.setText(client.getFullName());
                presenter.addClient(client);
            }
        });
        builder.show();
    }

    public ArrayList<Integer> getQuantity(){
        ArrayList<Integer> quantity = new ArrayList<>();
        int countItems = productList.getLayoutManager().getChildCount();
        for (int i = 0; i < countItems; i++ ){
            LinearLayout linearLayout = (LinearLayout) productList.getLayoutManager().findViewByPosition(i);
            EditText editText = (EditText) linearLayout.getChildAt(1);
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
        presenter.sale.setName(et_saleName.getText().toString());
        presenter.saveSale();
    }

    public void saveSaleSuccess(){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        onBackPressed();
    }

    public void saveSaleFail(String message){
        if (message.equals("product")){
            Toast.makeText(this,getResources().getString(R.string.message_sale_product_fail),Toast.LENGTH_SHORT).show();
        }
        else if (message.equals("client")){
            Toast.makeText(this,getResources().getString(R.string.message_product_client_fail),Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickbtnSelectClient(View view){
        presenter.loadClients();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void deleteSaleSucces() {

    }

    @Override
    public void loadSaleSuccess() {
        et_saleName.setText(presenter.getSale().getName());
        tv_saleClient.setText(presenter.getSale().getClient().getFullName());
    }

    public void setTextQuantity(){
        int countItems = productList.getLayoutManager().getChildCount();
        for (int i = 0; i < countItems; i++ ){
            LinearLayout linearLayout = (LinearLayout) productList.getLayoutManager().findViewByPosition(i);
            EditText editText = (EditText) linearLayout.getChildAt(1);
            try {
                editText.setText(String.valueOf(presenter.getSale().getProduct_quantity()));
            }
            catch(Exception e){
                editText.setText(String.valueOf(1));
            }
        }
    }

    @Override
    public void onItemClickListener(int id) {

    }
}
