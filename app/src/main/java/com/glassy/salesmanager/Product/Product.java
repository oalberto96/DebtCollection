package com.glassy.salesmanager.Product;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by glassy on 12/19/17.
 */

public class Product implements Parcelable {
    private int id;
    private String name;
    private float price;
    private String color;
    private String size;
    private String material;

    public Product(){

    }

    public Product(int id, String name, float price, String color, String size, String material) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.material = material;
    }

    public Product(String name, float price, String color, String size, String material) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.material = material;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readFloat();
        color = in.readString();
        size = in.readString();
        material = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
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
        dest.writeFloat(price);
        dest.writeString(color);
        dest.writeString(size);
        dest.writeString(material);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getMaterial() {
        return material;
    }


}
