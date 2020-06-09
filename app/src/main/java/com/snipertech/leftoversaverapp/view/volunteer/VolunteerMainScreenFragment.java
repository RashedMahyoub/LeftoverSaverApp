package com.snipertech.leftoversaverapp.view.volunteer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.controller.Dialog;
import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.controller.network.RetrofitInstance;
import com.snipertech.leftoversaverapp.model.Order;
import com.snipertech.leftoversaverapp.model.adapters.MyListAdapterVol;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VolunteerMainScreenFragment extends Fragment {

    RecyclerView recyclerView;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    MyListAdapterVol myListAdapterVol;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.volunteer_mainscreen, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_orders);

        getAllOrders();
        return view;
    }

    public void getAllOrders(){
        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Call<List<Order>> call = jsonPlaceHolderApi.getOrderList();

        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull final Response<List<Order>> response) {
                myListAdapterVol = new MyListAdapterVol(getContext(), response.body());
                recyclerView.setAdapter(myListAdapterVol);

                myListAdapterVol.setOnItemClickListener(new MyListAdapterVol.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                       Dialog dialog = new Dialog();
                       dialog.showDialog(getActivity(), response.body().get(position).getNeedyID(),
                                                        response.body().get(position).getNeedyPhoneNumber(),
                                                        response.body().get(position).getItemList(),
                                                        response.body().get(position).getDonorName());
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {

            }
        });
    }
}
