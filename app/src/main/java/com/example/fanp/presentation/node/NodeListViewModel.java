package com.example.fanp.presentation.node;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.node.add.AddNode;

import javax.inject.Inject;

import io.grpc.Context;

public class NodeListViewModel extends ViewModel {


    public NodeList main;


    @Inject
    public NodeListViewModel() {
    }


    public void add() {
        main.startActivity(new Intent(main, AddNode.class));
    }

    public void finish() {
        main.finish();
    }


}
