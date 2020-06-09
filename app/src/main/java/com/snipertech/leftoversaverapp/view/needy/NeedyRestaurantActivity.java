package com.snipertech.leftoversaverapp.view.needy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.controller.network.RetrofitInstance;
import com.snipertech.leftoversaverapp.model.Donor;
import com.snipertech.leftoversaverapp.model.Needy;
import com.snipertech.leftoversaverapp.model.adapters.MyListAdapterNeedy;

import java.util.ArrayList;
import java.util.List;

public class NeedyRestaurantActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText _txtSearch;
    Spinner filterCity;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    MyListAdapterNeedy myListAdapterNeedy;
    Needy needy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy_restaurant);

        recyclerView = findViewById(R.id.recyclerViewRest);
        _txtSearch = findViewById(R.id.searchDonor);
        filterCity = findViewById(R.id.filterCity);
        ArrayAdapter<CharSequence> city = ArrayAdapter.createFromResource(this, R.array.filter, R.layout.support_simple_spinner_dropdown_item);
        filterCity.setAdapter(city);

        needy = getIntent().getParcelableExtra("needy");
        getAllDonor();
    }

    public void getAllDonor(){
        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        Call<List<Donor>> call = jsonPlaceHolderApi.getDonorList();

        call.enqueue(new Callback<List<Donor>>() {
            @Override
            public void onResponse(@NonNull Call<List<Donor>> call, @NonNull final Response<List<Donor>> response) {
                final ArrayList<Donor> donors = new ArrayList<>(response.body());
                myListAdapterNeedy = new MyListAdapterNeedy(getApplicationContext(), donors);
                recyclerView.setAdapter(myListAdapterNeedy);

                myListAdapterNeedy.setOnItemClickListener(new MyListAdapterNeedy.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent =  new Intent(getApplicationContext(), NeedyRestItemActivity.class);
                        Donor donor = response.body().get(position);
                        intent.putExtra("ID", donor);
                        intent.putExtra("needy", needy);
                        startActivity(intent);
                    }
                });

                // Filter Function
                filterCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(filterCity.getSelectedItem().toString().equals("All")){
                            ArrayList<Donor> filteredList = new ArrayList<>(donors);
                            myListAdapterNeedy.filterList(filteredList);
                        }

                        String city = filterCity.getSelectedItem().toString();
                        Call<List<Donor>> filter = jsonPlaceHolderApi.filterDonor(city);
                        filter.enqueue(new Callback<List<Donor>>() {
                            @Override
                            public void onResponse(@NonNull Call<List<Donor>> call, @NonNull final Response<List<Donor>> response) {
                                ArrayList<Donor> filteredList = new ArrayList<>(response.body());
                                myListAdapterNeedy.filterList(filteredList);

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
                                        ArrayList<Donor> filteredList = new ArrayList<>();

                                        for (Donor donor : response.body()) {
                                            if (donor.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                                                filteredList.add(donor);
                                            }
                                        }

                                        myListAdapterNeedy.filterList(filteredList);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(@NonNull Call<List<Donor>> call, @NonNull Throwable t) {

                            }
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<List<Donor>> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred, try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
