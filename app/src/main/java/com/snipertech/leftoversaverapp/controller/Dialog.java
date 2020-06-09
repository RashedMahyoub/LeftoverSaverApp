package com.snipertech.leftoversaverapp.controller;


import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Item;

import java.util.List;

public class Dialog  {
    public void showDialog(Activity activity, String Name, String Phone, List<Item> ItemList, String ID){
        final android.app.Dialog dialog = new android.app.Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);

        TextView needyName = dialog.findViewById(R.id.text_name);
        TextView needyPhone = dialog.findViewById(R.id.text_phone);
        TextView needyItemList = dialog.findViewById(R.id.text_item);
        TextView donorID = dialog.findViewById(R.id.text_Id);

        needyName.setText(Name);
        needyPhone.setText(Phone);

        StringBuilder sb = new StringBuilder();
        for (Item item : ItemList)
        {
            sb.append(item.getName());
            sb.append(":");
            sb.append(item.getAmount());
            sb.append("||");
            sb.append("\t");
        }
        needyItemList.setText(sb);
        donorID.setText(ID);

        Button dialogButton = dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
