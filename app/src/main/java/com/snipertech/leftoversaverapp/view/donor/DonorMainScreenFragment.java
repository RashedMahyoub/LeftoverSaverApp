package com.snipertech.leftoversaverapp.view.donor;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.controller.DialogEditItem;
import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.controller.network.RetrofitInstance;
import com.snipertech.leftoversaverapp.model.Donor;
import com.snipertech.leftoversaverapp.model.Item;
import com.snipertech.leftoversaverapp.model.adapters.MyListAdapterDonor;
import com.snipertech.leftoversaverapp.view.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonorMainScreenFragment extends Fragment {

    private Button _btnAdd;
    private EditText _txtSearch;
    private RecyclerView recyclerView;
    private MyListAdapterDonor adapter;
    private DialogEditItem dialogEditItem;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Item> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.donor_mainscreen, container, false);

        _btnAdd = view.findViewById(R.id.donor_add);
        _txtSearch = view.findViewById(R.id.searchItem);
        recyclerView = view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.swipe_items);
        final Donor donor = ((DonorActivity)getActivity()).getDonor();
        dialogEditItem = new DialogEditItem();
        getAllItems();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllItems();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //swipe to delete functionality
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Item item = itemList.get(viewHolder.getAdapterPosition());
                deleteItem(item.getDonorName(), item.getName());
            }
        }).attachToRecyclerView(recyclerView);



        // Add Item Button
        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), DonorAddItemActivity.class);
//                intent.putExtra("ID", donor);
//                startActivity(intent);
                goToAdd();
            }
        });
        return view;
    }

    //go to add item activity
    private void goToAdd(){
        Intent intent = new Intent(getContext(), DonorAddItemActivity.class);
        String id =  Constants.getEndpoint();
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    //retrofit getitem list funcation
    private void getAllItems(){
        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        String id =  Constants.getName();
        Call<List<Item>> call = jsonPlaceHolderApi.getItemLists(id);

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(@NonNull Call<List<Item>> call, @NonNull final Response<List<Item>> response) {
                itemList = response.body();
                adapter = new MyListAdapterDonor(getContext(), response.body());
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new MyListAdapterDonor.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        dialogEditItem.showDialog(getActivity(), response.body().get(position).getName(), response.body().get(position).getAmount());
                    }
                });

                // Search Function
                _txtSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ArrayList<Item> filteredList = new ArrayList<>();

                        for (Item item : response.body()) {
                            if (item.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                                filteredList.add(item);
                            }
                        }

                        adapter.filterList(filteredList);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {

            }
        });
    }

    private void deleteItem(String id, String name){

        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);

        Call<Item> call = jsonPlaceHolderApi.delItem(id, name);

        call.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                Toast.makeText(getContext(), "Item Removed!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {

            }
        });
    }

}
