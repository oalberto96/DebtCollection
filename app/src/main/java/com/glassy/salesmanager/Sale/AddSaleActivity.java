package com.glassy.salesmanager.Sale;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;
import com.glassy.salesmanager.R;
import com.glassy.salesmanager.UI.ProductSaleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AddSaleActivity extends AppCompatActivity implements SaleView, ProductSaleAdapter.ListItemClickListener, ProductSaleAdapter.TotalListener, DatePickerDialog.OnDateSetListener{


    SalePresenter presenter;
    RecyclerView productList;
    LinearLayoutManager linearLayoutManager;
    int childCount;
    ProductSaleAdapter productAdapter;
    TextView tv_total;
    TextView tv_saleClient;
    TextView tvSaleDate;
    EditText et_saleName;
    Button btn_addSale;
    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);
        childCount = 0;
        presenter = new SalePresenter(this);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_saleClient = (TextView) findViewById(R.id.tv_sale_client);
        tvSaleDate = (TextView) findViewById(R.id.tv_sale_date);
        et_saleName = (EditText) findViewById(R.id.et_sale_name);
        btn_addSale = (Button) findViewById(R.id.btn_add_sale);
        datepickerInit();
        getExtraMessages();
        initRecyclerView(presenter.getProducts(), presenter.getSale().getProduct_quantity());
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

    public void datepickerInit(Date date){
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        tvSaleDate.setText("" + now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH));
        datePicker = new DatePickerDialog(
                getContext(), this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
    }


    public void datepickerInit(){
        Calendar now = Calendar.getInstance();
        tvSaleDate.setText("" + now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH));
        datePicker = new DatePickerDialog(
                getContext(), this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
    }

    public void onClickDatePicker(View view){
        datePicker.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        presenter.getSale().setDateSale(calendar.getTime());

        tvSaleDate.setText("" + year + "-" + month + 1 + "-" + dayOfMonth);
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
                presenter.addProduct(product);
            }
        });
        builder.show();
    }

    public void initRecyclerView(ArrayList<Product> products, ArrayList<Integer> quantity){
        productList = (RecyclerView) findViewById(R.id.rv_sales_products);
        linearLayoutManager = new LinearLayoutManager(this){
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
                if (childCount != presenter.getSale().products.size()){
                    productAdapter.lock = false;
                    childCount = presenter.getSale().products.size();
                }
            }
        };
        productList.setLayoutManager(linearLayoutManager);
        productList.setHasFixedSize(true);
        productAdapter = new ProductSaleAdapter(products, quantity, this, this);
        productList.setAdapter(productAdapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int id = viewHolder.getAdapterPosition();
                showDeleteSnackbar(id);
            }
        }).attachToRecyclerView(productList);
    }

    private void showDeleteSnackbar(int id) {
        presenter.addItemToList(id);
        Snackbar.make(findViewById(R.id.sale_activity_layout),
                R.string.client_deleted,
                Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.deleteItemFromList();
                        refreshProductList();
                    }
                }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                presenter.cleanList();
            }
        }).show();
    }

    @Override
    public void productAdded(ArrayList<Product> products){
        productAdapter.lock = true;
        productAdapter.notifyDataSetChanged();
        presenter.getTotal();
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
        datepickerInit(presenter.getSale().getDateSale());
        presenter.getTotal();
    }

    @Override
    public void refreshProductList() {
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(int id) {

    }

    @Override
    public void onViewTextChangedListener() {
        presenter.getTotal();
    }
}
