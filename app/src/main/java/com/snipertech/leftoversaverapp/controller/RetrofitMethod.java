package com.snipertech.leftoversaverapp.controller;

import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.model.Donor;
import com.snipertech.leftoversaverapp.model.Item;
import com.snipertech.leftoversaverapp.model.Needy;
import com.snipertech.leftoversaverapp.model.Order;
import com.snipertech.leftoversaverapp.model.Volunteer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitMethod {

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.18:8082/LeftoverSaverRest/webapi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void getAllNeedy(){
        Call<List<Needy>> call = jsonPlaceHolderApi
                .getNeedyList();

        call.enqueue(new Callback<List<Needy>>() {
            @Override
            public void onResponse(Call<List<Needy>> call, Response<List<Needy>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<Needy> needyList = response.body();

                for (Needy needy : needyList) {
                    String content = "";
                    content += "ID: " + needy.getUuid() + "\n";
                    content += "Name: " + needy.getEmail() + "\n";
                    content += "Address " + needy.getAddress() + "\n";
                    content += "PhoneNumber " + needy.getPhoneNumber() + "\n";
                    content += "Password" + needy.getPassword() + "\n\n";
                }
            }

            @Override
            public void onFailure(Call<List<Needy>> call, Throwable t) {
                return;
            }
        });
    }

    public void getAllDonor(){
        Call<List<Donor>> call = jsonPlaceHolderApi
                .getDonorList();

        call.enqueue(new Callback<List<Donor>>() {
            @Override
            public void onResponse(Call<List<Donor>> call, Response<List<Donor>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<Donor> donorsList = response.body();

                for (Donor donor : donorsList) {
                    String content = "";
                    content += "ID: " + donor.getUuid() + "\n";
                    content += "Name: " + donor.getEmail() + "\n";
                    content += "Address " + donor.getAddress() + "\n";
                    content += "PhoneNumber " + donor.getPhoneNumber() + "\n";
                    content += "Password" + donor.getPassword() + "\n\n";
                }
            }

            @Override
            public void onFailure(Call<List<Donor>> call, Throwable t) {
                return;
            }
        });
    }

    public void getAllVolunteer(){
        Call<List<Volunteer>> call = jsonPlaceHolderApi
                .getVolunteerList();

        call.enqueue(new Callback<List<Volunteer>>() {
            @Override
            public void onResponse(Call<List<Volunteer>> call, Response<List<Volunteer>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<Volunteer> volunteersList = response.body();

                for (Volunteer volunteer : volunteersList) {
                    String content = "";
                    content += "ID: " + volunteer.getUuid() + "\n";
                    content += "Name: " + volunteer.getEmail() + "\n";
                    content += "Address " + volunteer.getAddress() + "\n";
                    content += "PhoneNumber " + volunteer.getPhoneNumber() + "\n";
                    content += "Password" + volunteer.getPassword() + "\n\n";
                }
            }

            @Override
            public void onFailure(Call<List<Volunteer>> call, Throwable t) {
                return;
            }
        });
    }

    public void getAllOrder(){
        Call<List<Order>> call = jsonPlaceHolderApi
                .getOrderList();

        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<Order> orderList = response.body();

                for (Order order : orderList) {
                    String content = "";
                    content += "ID: " + order.getUuid() + "\n";
                    content += "needyId: " + order.getNeedyID() + "\n";
                    content += "needyPhoneNumber " + order.getNeedyPhoneNumber() + "\n\n";
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                return;
            }
        });
    }

    public void getAllItem(){
        Call<List<Item>> call = jsonPlaceHolderApi
                .getItemList();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                List<Item> itemsList = response.body();

                for (Item items : itemsList) {
                    String content = "";
                    content += "ID: " + items.getUuid() + "\n";
                    content += "Name: " + items.getName() + "\n";
                    content += "Amout: " + items.getAmount() + "\n\n";
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                return;
            }
        });
    }

    public void getNeedy(String Id){
        Call<Needy> call = jsonPlaceHolderApi
                .getNeedy(Id);

        call.enqueue(new Callback<Needy>() {
            @Override
            public void onResponse(Call<Needy> call, Response<Needy> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                String content = "";
                content += "ID: " + response.body().getUuid() + "\n";
                content += "Name: " + response.body().getEmail() + "\n";
                content += "Address: " + response.body().getAddress() + "\n";
                content += "PhoneNumber: " + response.body().getPhoneNumber() + "\n";
                content += "Password: " + response.body().getPassword() + "\n\n";

            }

            @Override
            public void onFailure(Call<Needy> call, Throwable t) {

            }
        });
    }



    public void getDonor(String Id){
        Call<Donor> call = jsonPlaceHolderApi
                .getDonor(Id);

        call.enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                String content = "";
                content += "ID: " + response.body().getUuid() + "\n";
                content += "Name: " + response.body().getEmail() + "\n";
                content += "Address: " + response.body().getAddress() + "\n";
                content += "PhoneNumber: " + response.body().getPhoneNumber() + "\n";
                content += "Password: " + response.body().getPassword() + "\n\n";

            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {

            }
        });
    }

    public void getOrder(String Id){
        Call<Order> call = jsonPlaceHolderApi
                .getOrder(Id);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                String content = "";
                content += "ID: " + response.body().getUuid() + "\n";
                content += "needyID: " + response.body().getNeedyID() + "\n";
                content += "needyPhoneNumber: " + response.body().getNeedyPhoneNumber() + "\n\n";
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }

    public void getVolunteer(String Id){
        Call<Volunteer> call = jsonPlaceHolderApi
                .getVolunteer(Id);

        call.enqueue(new Callback<Volunteer>() {
            @Override
            public void onResponse(Call<Volunteer> call, Response<Volunteer> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                String content = "";
                content += "ID: " + response.body().getUuid() + "\n";
                content += "Name: " + response.body().getEmail() + "\n";
                content += "Address: " + response.body().getAddress() + "\n";
                content += "PhoneNumber: " + response.body().getPhoneNumber() + "\n";
                content += "Password: " + response.body().getPassword() + "\n\n";

            }

            @Override
            public void onFailure(Call<Volunteer> call, Throwable t) {

            }
        });
    }

    public void getItem(String Id){
        Call<Item> call = jsonPlaceHolderApi
                .getItem(Id);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

                if (!response.isSuccessful()) {
                    return;
                }
                String content = "";
                content += "ID: " + response.body().getUuid() + "\n";
                content += "Name: " + response.body().getName() + "\n";
                content += "Amount: " + response.body().getAmount() + "\n\n";
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }



    public void insertNeedy(Needy n){
        Call<Needy> call = jsonPlaceHolderApi.insertNeedy(n);

        call.enqueue(new Callback<Needy>() {
            @Override
            public void onResponse(Call<Needy> call, Response<Needy> response) {

            }

            @Override
            public void onFailure(Call<Needy> call, Throwable t) {

            }
        });
    }

    public void insertDonor(Donor d){
        Call<Donor> call = jsonPlaceHolderApi.insertDonor(d);

        call.enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {

            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {

            }
        });
    }

    public void insertVolunteer(Volunteer v){
        Call<Volunteer> call = jsonPlaceHolderApi.insertVolunteer(v);

        call.enqueue(new Callback<Volunteer>() {
            @Override
            public void onResponse(Call<Volunteer> call, Response<Volunteer> response) {

            }

            @Override
            public void onFailure(Call<Volunteer> call, Throwable t) {

            }
        });
    }

    public void insertItem(Item i){
        Call<Item> call = jsonPlaceHolderApi.insertItem(i);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    public void deleteNeedy(String ID){
        Call<Needy> call = jsonPlaceHolderApi.delNeedy(ID);

        call.enqueue(new Callback<Needy>() {
            @Override
            public void onResponse(Call<Needy> call, Response<Needy> response) {

            }

            @Override
            public void onFailure(Call<Needy> call, Throwable t) {

            }
        });
    }

    public void deleteDonor(String ID){
        Call<Donor> call = jsonPlaceHolderApi.delDonor(ID);

        call.enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {

            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {

            }
        });
    }

    public void deleteVolunteer(String ID){
        Call<Volunteer> call = jsonPlaceHolderApi.delVolunteer(ID);

        call.enqueue(new Callback<Volunteer>() {
            @Override
            public void onResponse(Call<Volunteer> call, Response<Volunteer> response) {

            }

            @Override
            public void onFailure(Call<Volunteer> call, Throwable t) {

            }
        });
    }

    public void deleteOrder(String ID){
        Call<Order> call = jsonPlaceHolderApi.delOrder(ID);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {

            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }

    public void deleteItem(String ID, String name){
        Call<Item> call = jsonPlaceHolderApi.delItem(ID, name);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {

            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }
}
