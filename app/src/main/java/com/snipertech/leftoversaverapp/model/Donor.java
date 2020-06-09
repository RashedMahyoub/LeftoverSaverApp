package com.snipertech.leftoversaverapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.bson.types.ObjectId;

public class Donor implements Parcelable {
    ObjectId uuid;
    private String email;
    private String name;
    private String address;
    private String phoneNumber;
    private String password;

    public Donor(String email, String name, String address, String phoneNumber, String password) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(ObjectId uuid) {
        this.uuid = uuid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    protected Donor(Parcel in) {
        email = in.readString();
        name = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        password = in.readString();
    }

    public static final Creator<Donor> CREATOR = new Creator<Donor>() {
        @Override
        public Donor createFromParcel(Parcel in) {
            return new Donor(in);
        }

        @Override
        public Donor[] newArray(int size) {
            return new Donor[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phoneNumber);
        dest.writeString(password);
    }


}
