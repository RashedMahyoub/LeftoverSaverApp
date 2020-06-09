package com.snipertech.leftoversaverapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.controller.network.RetrofitInstance;
import com.snipertech.leftoversaverapp.model.Donor;
import com.snipertech.leftoversaverapp.model.Needy;
import com.snipertech.leftoversaverapp.model.Volunteer;

public class RegisterActivity extends AppCompatActivity {

    Button _btnRegister, _btnSwitch;
    EditText _txtEmail, _txtName, _txtPhonenum, _txtPass;
    Spinner _spinner, _address;
    String type = "reg";
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _btnRegister = findViewById(R.id.register);
        _txtEmail = findViewById(R.id.username);
        _txtName = findViewById(R.id.Name);
        _txtPhonenum = findViewById(R.id.phone);
        _address = findViewById(R.id.address);
        _txtPass = findViewById(R.id.password);
        _spinner = findViewById(R.id.spinner);
        _btnSwitch = findViewById(R.id.switchto);


        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);

        _btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> city = ArrayAdapter.createFromResource(this, R.array.city, R.layout.support_simple_spinner_dropdown_item);
        _address.setAdapter(city);

        _btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();
                String email = _txtEmail.getText().toString();
                String name = _txtName.getText().toString();
                String address = _address.getSelectedItem().toString();
                String phoneNumber = _txtPhonenum.getText().toString();
                String Password = _txtPass.getText().toString();

                if(email.matches("")){
                    Toast.makeText(getApplicationContext(), "Enter username please ", Toast.LENGTH_LONG).show();
                }else if(name.matches("")){
                    Toast.makeText(getApplicationContext(), "Enter name please ", Toast.LENGTH_LONG).show();
                }else if(phoneNumber.matches("")){
                    Toast.makeText(getApplicationContext(), "Enter phone number please ", Toast.LENGTH_LONG).show();
                }else if(Password.matches("")){
                    Toast.makeText(getApplicationContext(), "Enter password please ", Toast.LENGTH_LONG).show();
                }else if(address.matches("")){
                    Toast.makeText(getApplicationContext(), "Choose city please ", Toast.LENGTH_LONG).show();
                }else {
                    switch (item) {
                        case "Needy":
                            register("needy", email, name, address, phoneNumber, Password);
                            break;
                        case "Donor":
                            register("donor", email, name, address, phoneNumber, Password);
                            break;
                        case "Volunteer":
                            register("vol", email, name, address, phoneNumber, Password);
                            break;
                    }
                }
            }
        });
    }

    public void register(String user, String email, String name, String address, String phone, String pass){
        switch (user) {
            case "needy": {
                Needy n = new Needy(email, name, address, phone, pass);

                Call<Needy> call = jsonPlaceHolderApi.insertNeedy(n);

                call.enqueue(new Callback<Needy>() {
                    @Override
                    public void onResponse(@NonNull Call<Needy> call, @NonNull Response<Needy> response) {
                        Toast.makeText(getApplicationContext(), "Successfully added...Welcome!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplication(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Needy> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "An error has occurred, please try again later!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            case "donor": {
                Donor d = new Donor(email, name, address, phone, pass);

                Call<Donor> call = jsonPlaceHolderApi.insertDonor(d);

                call.enqueue(new Callback<Donor>() {
                    @Override
                    public void onResponse(@NonNull Call<Donor> call, @NonNull Response<Donor> response) {
                        Toast.makeText(getApplicationContext(), "Successfully added...Welcome!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplication(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Donor> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "An error has occurred, please try again later!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            case "vol": {
                Volunteer v = new Volunteer(email, name, address, phone, pass);

                Call<Volunteer> call = jsonPlaceHolderApi.insertVolunteer(v);

                call.enqueue(new Callback<Volunteer>() {
                    @Override
                    public void onResponse(@NonNull Call<Volunteer> call, @NonNull Response<Volunteer> response) {
                        Toast.makeText(getApplicationContext(), "Successfully added...Welcome!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplication(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Volunteer> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "An error has occurred, please try again later!", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
        }
    }
}
