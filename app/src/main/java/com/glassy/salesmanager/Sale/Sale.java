package com.glassy.salesmanager.Sale;

import android.os.Parcel;
import android.os.Parcelable;

import com.glassy.salesmanager.Client.Client;
import com.glassy.salesmanager.Product.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by glassy on 12/19/17.
 */

public class Sale implements Parcelable {
    protected int id;
    protected String name;
    protected Client client;
    protected ArrayList<Product> products;
    protected ArrayList<Integer> product_quantity;
    protected Date dateSale;

    public ArrayList<Integer> getProduct_quantity() {
        return product_quantity;
    }

    public Client getClient() {
        return client;
    }


    public Sale(){
        products = new ArrayList<>();
    }

    public Sale(int id, String name, Client client, Timestamp date){
        this.id = id;
        this.client = client;
        this.name = name;
        this.dateSale = date;
    }

    public Sale(String name, Client client, ArrayList<Product> products, ArrayList<Integer> product_quantity, Date dateSale) {
        this.name = name;
        this.client = client;
        this.products = products;
        this.product_quantity = product_quantity;
        this.dateSale = dateSale;
    }

    public Sale(int id, String name, Client client, ArrayList<Product> products, ArrayList<Integer> product_quantity, Date dateSale) {
        this.id = id;
        this.name = name;
        this.client = client;
        this.products = products;
        this.product_quantity = product_quantity;
        this.dateSale = dateSale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateSale() {
        return dateSale;
    }

    protected Sale(Parcel in) {
        id = in.readInt();
        name = in.readString();
        dateSale = new Date(in.readLong());
    }

    public static final Creator<Sale> CREATOR = new Creator<Sale>() {
        @Override
        public Sale createFromParcel(Parcel in) {
            return new Sale(in);
        }

        @Override
        public Sale[] newArray(int size) {
            return new Sale[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeLong(dateSale.getTime());
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setQuantity(ArrayList<Integer> quantity){
        this.product_quantity = quantity;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setId(int id) {
        this.id = id;
    }
}
