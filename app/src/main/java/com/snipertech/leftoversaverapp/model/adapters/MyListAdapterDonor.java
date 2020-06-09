package com.snipertech.leftoversaverapp.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Item;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapterDonor extends RecyclerView.Adapter<MyListAdapterDonor.ViewHolder>{
    private List<Item> listdata;
    private LayoutInflater mInflater;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    // RecyclerView recyclerView;
    public MyListAdapterDonor(Context context, List<Item> listdata) {
        this.mInflater = LayoutInflater.from(context);
        this.listdata = listdata;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = mInflater.inflate(R.layout.item_list, parent, false);
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
        public TextView name, amount;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.name_edit_text);
            amount = itemView.findViewById(R.id.name_text_amount);
            relativeLayout = itemView.findViewById(R.id.relative);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public void filterList(ArrayList<Item> filteredList) {
        listdata = filteredList;
        notifyDataSetChanged();
    }
}
