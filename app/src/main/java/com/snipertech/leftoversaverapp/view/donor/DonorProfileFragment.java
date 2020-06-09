package com.snipertech.leftoversaverapp.view.donor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Donor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DonorProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.donor_profile, container, false);

        TextView _txtName = view.findViewById(R.id.donor_name);
        TextView _txtEmail = view.findViewById(R.id.donor_email);
        TextView _txtAddress = view.findViewById(R.id.donor_address);
        TextView _txtPhone = view.findViewById(R.id.donor_phone);



        Donor donor = ((DonorActivity)getActivity()).getDonor();
        _txtName.setText(donor.getName());
        _txtEmail.setText(donor.getEmail());
        _txtAddress.setText(donor.getAddress());
        _txtPhone.setText(donor.getPhoneNumber());

        return view;
    }
}
