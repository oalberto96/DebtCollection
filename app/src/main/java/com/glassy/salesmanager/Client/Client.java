package com.glassy.salesmanager.Client;

import android.os.Parcel;
import android.os.Parcelable;

import com.glassy.salesmanager.MVP.Models.User;

/**
 * Created by glassy on 12/16/17.
 */

public class Client extends User implements Parcelable {
    private int id;
    private String address;
    private String phoneNumber;
    private String notes;
    private String tin;

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public String getTin() {
        return tin;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {this.id = id;}


    public Client(String first_name, String last_name) {
        super(first_name,last_name);
    }

    public Client(int id,String first_name, String last_name, String address, String phoneNumber, String notes, String tin) {
        super(first_name, last_name);
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.tin = tin;
    }

    public Client(String first_name, String last_name, String address, String phoneNumber, String notes, String tin) {
        super(first_name, last_name);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.tin = tin;
    }

    protected Client(Parcel in) {
        super(in.readString(),in.readString());
        id = in.readInt();
        address = in.readString();
        phoneNumber = in.readString();
        notes = in.readString();
        tin = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(super.first_name);
        parcel.writeString(super.last_name);
        parcel.writeInt(id);
        parcel.writeString(address);
        parcel.writeString(phoneNumber);
        parcel.writeString(notes);
        parcel.writeString(tin);

    }
}
