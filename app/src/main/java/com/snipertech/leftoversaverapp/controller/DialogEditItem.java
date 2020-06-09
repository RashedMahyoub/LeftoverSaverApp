package com.snipertech.leftoversaverapp.controller;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.controller.network.RetrofitInstance;
import com.snipertech.leftoversaverapp.model.Item;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogEditItem {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private int counter = 0;

    public void showDialog(final Activity activity, final String Name, final String Amount){
        final android.app.Dialog dialog = new android.app.Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_edit_item);

        TextView itemName = dialog.findViewById(R.id.text_name);
        final TextView itemAmount = dialog.findViewById(R.id.text_amount);
        Button add = dialog.findViewById(R.id.add);
        Button remove = dialog.findViewById(R.id.del);
        counter = 0;
        counter += Integer.parseInt(Amount);

        itemName.setText(Name);
        itemAmount.setText(String.valueOf(counter));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                itemAmount.setText(String.valueOf(counter));
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                itemAmount.setText(String.valueOf(counter));
            }
        });


        Button dialogButton = dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(activity, Name, String.valueOf(counter));
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void update(final Activity activity, String itemName, String itemAmount){
        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);

        Call<Item> call = jsonPlaceHolderApi.updateItem(itemName, itemAmount);
        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                Toast.makeText(activity, "Done!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {
            }
        });
    }
}
