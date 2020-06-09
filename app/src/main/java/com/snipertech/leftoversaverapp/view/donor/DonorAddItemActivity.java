package com.snipertech.leftoversaverapp.view.donor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.controller.network.RetrofitInstance;
import com.snipertech.leftoversaverapp.model.Item;
import com.snipertech.leftoversaverapp.view.Constants;

public class DonorAddItemActivity extends AppCompatActivity {

    Button _btnAdd;
    EditText _txtName, _txtAmount;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_add_item);


        _btnAdd = findViewById(R.id.addItems);
        _txtAmount = findViewById(R.id.item_amount);
        _txtName = findViewById(R.id.item_name);

        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(_txtName.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please add the name of the item", Toast.LENGTH_LONG).show();
                }else if(_txtAmount.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please add the amount of the item", Toast.LENGTH_LONG).show();
                }else{
                    String donorId = Constants.getName();
                    String email = _txtName.getText().toString().toUpperCase();
                    String amount = _txtAmount.getText().toString().toUpperCase();
                    Item item = new Item(donorId, email, amount);
                    addItem(item);
                }
            }
        });

    }

    public void addItem(Item n){
        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);

        Call<Item> call = jsonPlaceHolderApi.insertItem(n);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), DonorActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull  Call<Item> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
