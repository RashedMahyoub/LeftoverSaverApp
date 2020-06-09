package com.snipertech.leftoversaverapp.model;

import org.bson.types.ObjectId;

public class Item {
    ObjectId uuid;
    String donorName;
    String name;
    String amount;


    public Item(String donorName, String name, String amount) {
        this.donorName = donorName;
        this.name = name;
        this.amount = amount;
    }

    public void setUuid(ObjectId uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        donorName = donorName;
    }

    public ObjectId getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean isEqual= false;

        if (object instanceof Item)
        {
            isEqual = (this.getName() == ((Item) object).getName());
        }

        return isEqual;
    }
}
