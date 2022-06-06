package com.example.fanp.presentation.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fanp.R;
import com.example.fanp.utils.BasicFragment;

import javax.inject.Inject;

public class HomeFragment extends BasicFragment {


    @Inject
    public HomeFragment(){}

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,parent,false);

        //Now specific components here (you can initialize Buttons etc)

        return view;
    }


}
