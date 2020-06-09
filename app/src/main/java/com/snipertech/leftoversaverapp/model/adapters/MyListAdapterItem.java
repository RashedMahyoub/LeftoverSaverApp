package com.snipertech.leftoversaverapp.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapterItem extends RecyclerView.Adapter<MyListAdapterItem.ViewHolder>{
    private List<Item> listdata;
    private LayoutInflater mInflater;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemAddClick(int position, int counter);
        void onItemDelClick(int position, int counter);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    // RecyclerView recyclerView;
    public MyListAdapterItem(Context context, List<Item> listdata) {
        this.mInflater = LayoutInflater.from(context);
        this.listdata = listdata;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = mInflater.inflate(R.layout.food_list, parent, false);
        return new ViewHolder(listItem, mListener);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = listdata.get(position);
        holder.name.setText(item.getName());
        holder.amount.setText(item.getAmount());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, amount, quantity;
        public Button btn_add, btn_del;
        public RelativeLayout relativeLayout;
        int counter = 0;
        public ViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            amount = itemView.findViewById(R.id.item_amount);
            quantity = itemView.findViewById(R.id.name_text_input);
            btn_add = itemView.findViewById(R.id.add);
            btn_del = itemView.findViewById(R.id.del);
            relativeLayout = itemView.findViewById(R.id.food_list);

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        counter++;
                        quantity.setText("" + counter);

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemAddClick(position, counter);
                        }
                    }
                }
            });

            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        if(counter > 0){
                            counter--;
                            quantity.setText("" + counter);
                        }

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemDelClick(position, counter);
                        }
                    }
                }
            });
        }
    }
}