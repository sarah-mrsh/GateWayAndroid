package com.example.fanp.presentation.s7.manageplc.functionblock.parameters.addparameter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.presentation.s7.manageplc.functionblock.parameters.AdapterPrameterFunctionBloc;
import com.example.fanp.utils.NameEdt;

import javax.inject.Inject;

public class AddParameterViewModel extends ViewModel {


    public String name;
    public String type;
    public String plcname;
    public String address;
    public String bitnumber;
    public String description;

    public AddParameterFunctionBlock main;

    @Inject
    Context ctx;

    @Inject
    public AddParameterViewModel() {
    }

    public void validation(NameEdt name){

        if(!name.valid){

            Toast.makeText(ctx, "Name is not valid.", Toast.LENGTH_SHORT).show();
            return;
        }
        savedata();

    }

    public void savedata() {

    }

    public void onSelectType(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        type = parent.getAdapter().getItem(pos).toString();
    }


    public void onSelectPlc(AdapterView<?> parent, View view, int pos, long id) {
        //pos                                 get selected item position
        //view.getText()                      get label of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item

        plcname = parent.getAdapter().getItem(pos).toString();
    }





    public void exit() {
        main.finish();
    }

}
