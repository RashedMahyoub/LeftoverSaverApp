package com.snipertech.leftoversaverapp.model;

import org.bson.types.ObjectId;

import java.util.List;

public class Order {
    ObjectId uuid;
    String donorName;
    List<Item> itemList;
    private String needyID;
    private String needyPhoneNumber;

    public Order( String donorName, List<Item> itemList, String needyID, String needyPhoneNumber) {
        this.donorName = donorName;
        this.itemList = itemList;
        this.needyID = needyID;
        this.needyPhoneNumber = needyPhoneNumber;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getNeedyID() {
        return needyID;
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public String getNeedyPhoneNumber() {
        return needyPhoneNumber;
    }

}
