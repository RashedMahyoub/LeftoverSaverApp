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

import com.snipertech.leftoversaverapp.view.donor.DonorActivity;
import com.snipertech.leftoversaverapp.view.needy.NeedyActivity;
import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.view.volunteer.VolunteerActivity;
import com.snipertech.leftoversaverapp.controller.network.JsonPlaceHolderApi;
import com.snipertech.leftoversaverapp.controller.network.RetrofitInstance;
import com.snipertech.leftoversaverapp.model.Donor;
import com.snipertech.leftoversaverapp.model.Needy;
import com.snipertech.leftoversaverapp.model.Volunteer;

public class LoginActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    Button _btnLogin;
    EditText _txtEmail, _txtPass;
    Spinner _spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _btnLogin = findViewById(R.id.login);
        _txtEmail = findViewById(R.id.username2);
        _txtPass = findViewById(R.id.password2);
        _spinner = findViewById(R.id.spinner2);


        jsonPlaceHolderApi = RetrofitInstance.getRetrofitInstance().create(JsonPlaceHolderApi.class);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);

        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();
                if(_txtEmail.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Enter email please ", Toast.LENGTH_LONG).show();
                }else if(_txtPass.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "Enter password please ", Toast.LENGTH_LONG).show();
                }else {
                    switch (item) {
                        case "Needy":
                            login("needy", _txtEmail.getText().toString(), _txtPass.getText().toString());
                            break;
                        case "Donor":
                            login("donor", _txtEmail.getText().toString(), _txtPass.getText().toString());
                            break;
                        case "Volunteer":
                            login("vol", _txtEmail.getText().toString(), _txtPass.getText().toString());
                            break;
                    }
                }
            }
        });
    }

    public void login(String user, final String email, final String password){
        switch (user) {
            case "needy": {
                Constants.setEndpoint(email);

                Call<Needy> call = jsonPlaceHolderApi.searchNeedy(email, password);
                call.enqueue(new Callback<Needy>() {
                    @Override
                    public void onResponse(@NonNull Call<Needy> call, @NonNull Response<Needy> response) {
                        if (response.body() != null) {
                            Intent intent = new Intent(getApplicationContext(), NeedyActivity.class);
                            intent.putExtra("needy", response.body());
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Welcome " + response.body().getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong username or password!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Needy> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Sorry there is an error, try again later ", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            case "donor": {
                Constants.setEndpoint(email);

                Call<Donor> call = jsonPlaceHolderApi.searchDonor(email, password);

                call.enqueue(new Callback<Donor>() {
                    @Override
                    public void onResponse(@NonNull Call<Donor> call, @NonNull Response<Donor> response) {
                        if (response.body() != null) {
                            Intent intent = new Intent(getApplicationContext(), DonorActivity.class);
                            intent.putExtra("donor", response.body());
                            Constants.setName(response.body().getName());
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Welcome " + response.body().getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong username or password!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Donor> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Sorry there is an error, try again later ", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            case "vol": {
                Constants.setEndpoint(email);
                Call<Volunteer> call = jsonPlaceHolderApi.searchVolunteer(email, password);

                call.enqueue(new Callback<Volunteer>() {
                    @Override
                    public void onResponse(@NonNull Call<Volunteer> call, @NonNull Response<Volunteer> response) {
                        if (response.body() != null) {
                            Intent intent = new Intent(getApplicationContext(), VolunteerActivity.class);
                            intent.putExtra("vol", response.body());
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Welcome " + response.body().getName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong username or password!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Volunteer> call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Sorry there is an error, try again later ", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
        }
    }
}
