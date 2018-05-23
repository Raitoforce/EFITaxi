package com.efitaxi.technous.efitaxi.Activity.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.efitaxi.technous.efitaxi.Activity.MainActivity;
import com.efitaxi.technous.efitaxi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    Button buttonLogin;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login_fragment_layaout,container,false);
        buttonLogin=(Button) view.findViewById(R.id.btnEntrar);
        buttonLogin.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        Intent intentMain=new Intent(getContext(), MainActivity.class);
        startActivity(intentMain);
    }
}
