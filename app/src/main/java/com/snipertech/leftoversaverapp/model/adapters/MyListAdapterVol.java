package com.snipertech.leftoversaverapp.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Order;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapterVol extends RecyclerView.Adapter<MyListAdapterVol.ViewHolder>{
    private List<Order> listdata;
    private LayoutInflater mInflater;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // RecyclerView recyclerView;
    public MyListAdapterVol(Context context, List<Order> listdata) {
        this.mInflater = LayoutInflater.from(context);
        this.listdata = listdata;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listOrder= mInflater.inflate(R.layout.order_list, parent, false);
        return new ViewHolder(listOrder, mListener);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order order = listdata.get(position);
        holder.name.setText(order.getNeedyID());
        holder.phoneNumber.setText(order.getNeedyPhoneNumber());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phoneNumber;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.name_edit_text);
            phoneNumber = itemView.findViewById(R.id.phone);
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
}