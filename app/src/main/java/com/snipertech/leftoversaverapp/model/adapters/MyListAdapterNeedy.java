package com.snipertech.leftoversaverapp.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Donor;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapterNeedy extends RecyclerView.Adapter<MyListAdapterNeedy.ViewHolder> {
    private List<Donor> donorList;
    private LayoutInflater mInflater;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MyListAdapterNeedy(Context context, List<Donor> donors){
        this.mInflater = LayoutInflater.from(context);
        this.donorList = donors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = mInflater.inflate(R.layout.donor_list, parent, false);
        return new ViewHolder(listItem, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Donor donor = donorList.get(position);
        holder.name.setText(donor.getName());
    }

    @Override
    public int getItemCount() {
        return donorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.name_edit_text);
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
    public void filterList(ArrayList<Donor> filteredList) {
        donorList = filteredList;
        notifyDataSetChanged();
    }
}
