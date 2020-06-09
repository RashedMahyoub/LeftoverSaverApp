package com.snipertech.leftoversaverapp.view.needy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Needy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NeedyProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.needy_profile, container, false);

        TextView _txtName = view.findViewById(R.id.needy_name);
        TextView _txtEmail = view.findViewById(R.id.needy_email);
        TextView _txtAddress = view.findViewById(R.id.needy_address);
        TextView _txtPhone = view.findViewById(R.id.needy_phone);

        Needy needy = ((NeedyActivity)getActivity()).getNeedy();
        _txtName.setText(needy.getName());
        _txtEmail.setText(needy.getEmail());
        _txtAddress.setText(needy.getAddress());
        _txtPhone.setText(needy.getPhoneNumber());

        return view;
    }
}
