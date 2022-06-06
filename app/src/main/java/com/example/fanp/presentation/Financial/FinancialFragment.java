package com.example.fanp.presentation.Financial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fanp.R;
import com.example.fanp.utils.BasicFragment;

import javax.inject.Inject;

public class FinancialFragment extends BasicFragment {



    @Inject
    public FinancialFragment(){}

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_financial,parent,false);

        //Now specific components here (you can initialize Buttons etc)

        return view;
    }

}
