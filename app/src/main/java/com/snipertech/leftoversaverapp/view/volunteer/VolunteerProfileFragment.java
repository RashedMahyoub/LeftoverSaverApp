package com.snipertech.leftoversaverapp.view.volunteer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Volunteer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VolunteerProfileFragment extends Fragment {

    TextView _txtName, _txtEmail, _txtPhone, _txtAddress;
    Volunteer volunteer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.volunteer_profile, container, false);
        _txtName = view.findViewById(R.id.vol_name);
        _txtEmail = view.findViewById(R.id.vol_Email);
        _txtAddress = view.findViewById(R.id.vol_address);
        _txtPhone = view.findViewById(R.id.vol_phone);

        volunteer = ((VolunteerActivity)getActivity()).getVolunteer();

        _txtName.setText(volunteer.getName());
        _txtEmail.setText(volunteer.getEmail());
        _txtAddress.setText(volunteer.getAddress());
        _txtPhone.setText(volunteer.getPhoneNumber());
        return view;
    }
}
