package com.snipertech.leftoversaverapp.view.needy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.controller.network.RetrofitInstance;
import com.snipertech.leftoversaverapp.model.Donor;
import com.snipertech.leftoversaverapp.model.Item;
import com.snipertech.leftoversaverapp.model.Needy;
import com.snipertech.leftoversaverapp.model.Order;
import com.snipertech.leftoversaverapp.model.adapters.MyListAdapterItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeedyRestItemActivity extends AppCompatActivity {

    TextView _txtRestName;
    Button btn_confirm;
    RecyclerView recyclerView;
    List<String> amountList = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    MyListAdapterItem myListAdapterItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy_rest_item);

        _txtRestName = findViewById(R.id.rest_name);
        recyclerView = findViewById(R.id.recyclerViewRestItem);
        btn_confirm = findViewById(R.id.confirm);


        final Needy needy = getIntent().getParcelableExtra("needy");
        final Donor donor = getIntent().getParcelableExtra("ID");

        _txtRestName.setText(donor.getName());
        getAllItems(donor.getName());

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order(donor.getName(), itemList, needy.getName(), needy.getPhoneNumber());
                addOrder(order);

                for(int i = 0; i < itemList.size(); i++){
                    Call<Item> call = jsonPlaceHolderApi.updateItem(itemList.get(i).getName(),
                            String.valueOf(Integer.parseInt(amountList.get(i)) - Integer.parseInt(itemList.get(i).getAmount())));
                    call.enqueue(new Callback<Item>() {
                        @Override
                        public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {

                        }

                        @Override
                        public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {

                        }
                    });
                }
            }
        });
    }

    public void getAllItems(String id){
        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        Call<List<Item>> call = jsonPlaceHolderApi.getItemLists(id);


        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(@NonNull final Call<List<Item>> call, @NonNull final Response<List<Item>> response) {
                myListAdapterItem = new MyListAdapterItem(getApplicationContext(), response.body());
                recyclerView.setAdapter(myListAdapterItem);

                myListAdapterItem.setOnItemClickListener(new MyListAdapterItem.OnItemClickListener() {
                    @Override
                    public void onItemAddClick(int position, int counter) {
                        // iterate through the list to see if current item already exists
                        if(itemList.size() > 0) {
                            for (int i = 0; i < itemList.size(); i++) {
                                if (itemList.get(i).getName().equals(response.body().get(position).getName())) {
                                    itemList.get(i).setAmount(String.valueOf(counter));
                                }
                            }
                            if(!itemList.contains(response.body().get(position))){
                                itemList.add(response.body().get(position));
                                amountList.add(response.body().get(position).getAmount());
                            }

                        }else{
                            itemList.add(response.body().get(position));
                            amountList.add(response.body().get(position).getAmount());
                        }

                        if (Integer.parseInt(response.body().get(position).getAmount()) < counter) {
                            Toast.makeText(getApplicationContext(), "Sorry but this item has no amount left!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onItemDelClick(int position, int counter) {
                        for (Item item : itemList) {
                            if (item.getName().equals(response.body().get(position).getName())) {
                                item.setAmount(String.valueOf(counter));

                                // Update the item
                                Call<Item> call = jsonPlaceHolderApi.updateItem(response.body().get(position).getName(),
                                        String.valueOf(Integer.parseInt(response.body().get(position).getAmount()) + 1));

                                call.enqueue(new Callback<Item>() {
                                    @Override
                                    public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                                        Toast.makeText(getApplicationContext(), "Minus", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {

                                    }
                                });
                            }
                        }
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {

            }
        });
    }

    public void addOrder(Order order){
        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        Call<Order> call = jsonPlaceHolderApi.insertOrder(order);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), NeedyRestaurantActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull Call<Order> call,@NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Adding Order!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
