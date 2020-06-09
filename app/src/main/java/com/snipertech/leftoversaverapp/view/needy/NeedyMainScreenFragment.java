package com.snipertech.leftoversaverapp.view.needy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.snipertech.leftoversaverapp.R;
import com.snipertech.leftoversaverapp.model.Needy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NeedyMainScreenFragment extends Fragment {

    private Button _btnAdd;
    TextView v_Welcome;
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.needy_mainscreen, container, false);

        _btnAdd = view.findViewById(R.id.needy_order);
        v_Welcome = view.findViewById(R.id.needy_welcome);

        final Needy needy = ((NeedyActivity)getActivity()).getNeedy();
        v_Welcome.setText("Hello " + needy.getName());


        _btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder(needy);
            }
        });
        return view;
    }

    public void addOrder(Needy needy){
        Intent intent = new Intent(getContext(), NeedyRestaurantActivity.class);
        intent.putExtra("needy", needy);
        startActivity(intent);
    }
}
