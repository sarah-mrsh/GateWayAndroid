package com.example.fanp.presentation.s7.tag.datablock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.fanp.R;
import com.example.fanp.databinding.ActivityAddDatatBlockTagBinding;
import com.example.fanp.di.injector.ViewModelProviderFactory;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.manageplc.datablockplc.AdapterDataBlockPlcList;
import com.example.fanp.utils.FixSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AddDatatBlockTag extends DaggerAppCompatActivity {
    AddDatatBlockTag main;

    @Inject
    ViewModelProviderFactory providerFactory;

    boolean firstinitialize = true;

    @Inject
    Context xcs;

    @Inject
    I4AllSettingDao db;

    ActivityAddDatatBlockTagBinding binding;
    AddTagDataBlockViewModel viewmodel;
    List<String> plcs = new ArrayList<>();
    public static boolean update = false;
    public static I4AllSetting item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_datat_block_tag);
        viewmodel = ViewModelProviders.of(this, providerFactory).get(AddTagDataBlockViewModel.class);
        binding.setViewmodel(viewmodel);
        viewmodel.main = this;


        binding.edtname.setEnabled(false);
        binding.edtaddress.setEnabled(false);
        binding.edtbitnumber.setEnabled(false);
        binding.sptype.setEnabled(false);
        binding.edtparameterid.setEnabled(false);
        binding.edtparamdescription.setEnabled(false);

        List<I4AllSetting> spdata = db.getitembyitesref(600);

        for (I4AllSetting item : spdata) {
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                plcs.add(object.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (plcs.size() == 0) {
            Toast.makeText(xcs, "DataBlock IS NULL", Toast.LENGTH_SHORT).show();
            finish();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, plcs);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spplc.setAdapter(adapter);
        binding.spplc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//on select dataBlock
                try {
                    if (firstinitialize) {
                        firstinitialize = false;
                        return;
                    }
//
                    Dialog parameter = new Dialog(main);
                    View dview = LayoutInflater.from(main).inflate(R.layout.dialog_parameter_list, null, false);
                    parameter.setContentView(dview);
                    RecyclerView recparameter = (RecyclerView) dview.findViewById(R.id.rec_parameter);

                    AdapterParameter adapterParameter = null;

                    List<I4AllSetting> datablocks = db.getitembyitesref(600);

                    for (I4AllSetting blocks : datablocks) {
                        JSONObject tmp = new JSONObject(blocks.getItemsData());
                        if (tmp.getString("name").equals(binding.spplc.getSelectedItem().toString())) {// on select datablock

                            viewmodel.dbid = tmp.getString("id");

                            if (tmp.getString("functionblock").equals("0")) {
                                binding.edtname.setText("");
                                binding.edtaddress.setText("");
                                binding.edtbitnumber.setText("");
                                binding.edtparameterid.setText("");
                                binding.edtparamdescription.setText("");
                                binding.edtname.setEnabled(true);
                                binding.edtaddress.setEnabled(true);
                                binding.edtbitnumber.setEnabled(true);
                                binding.sptype.setEnabled(true);
                                binding.edtparameterid.setEnabled(true);
                                binding.edtparamdescription.setEnabled(true);
                                viewmodel.datablockparameter = "0";
                                viewmodel.functionblocknumber = "0";
                                return;
                            } else {
                                binding.edtname.setText("");
                                binding.edtaddress.setText("");
                                binding.edtbitnumber.setText("");
                                binding.edtparameterid.setText("");
                                binding.edtparamdescription.setText("");

                                binding.edtname.setEnabled(false);
                                binding.edtaddress.setEnabled(false);
                                binding.edtbitnumber.setEnabled(false);
                                binding.sptype.setEnabled(false);
                                binding.edtparameterid.setEnabled(false);
                                binding.edtparamdescription.setEnabled(false);
                            }


                            int id_functionblock = 0;
                            List<I4AllSetting> functions = db.getitembyitesref(601);
                            for (I4AllSetting item : functions) {
                                JSONObject tmpfunction = new JSONObject(item.getItemsData());//on select functionblock
                                if (tmpfunction.getString("functionblockname").equals(tmp.getString("functionblock"))) {
                                    id_functionblock = tmpfunction.getInt("functionblocknumber");
//                                    viewmodel.function =tmpfunction.getString("plcname");
                                    break;
                                }
                            }


                            List<I4AllSetting> list = db.getitembyitesref(id_functionblock);
                            adapterParameter = new AdapterParameter(getApplicationContext(), new ParameterImpl() {
                                @Override
                                public void onSelectParameter(I4AllSetting selected) {
//                                    binding.spplc.setOnItemSelectedListener(null);
                                    try {


                                        fill_parameter(selected);

                                        parameter.dismiss();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, list);
                            break;
                        }
                    }
                    if (adapterParameter != null) {

                        LinearLayoutManager manager = new LinearLayoutManager(xcs);
                        recparameter.setLayoutManager(manager);
                        recparameter.setAdapter(adapterParameter);
                        parameter.show();
                    } else {
                        Toast.makeText(xcs, "There isn't any connected parameter", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (update) {
            binding.tagid.setEnabled(false);
            binding.spplc.setEnabled(false);
            try {
                JSONObject object = new JSONObject(item.getItemsData());
                if (object.has("tagname")) {
                    viewmodel.tagname = object.getString("tagname");
                }
                if (object.has("tagid")) {
                    viewmodel.tagid = object.getString("tagid");
                }
                String dbname = "";
                if (object.has("dbid")) {
                    List<I4AllSetting> dblist = db.getitembyitesref(600);
                    for (I4AllSetting item : dblist) {
                        JSONObject dbtemp = new JSONObject(item.getItemsData());
                        if (dbtemp.getInt("id") == object.getInt("dbid")) {
                            dbname = dbtemp.getString("name");
                        }
                    }
                    for (int i = 0; i < plcs.size(); i++) {
                        if (plcs.get(i).equals(dbname)) {
                            FixSpinner.forc_disable = true;
                            binding.spplc.setSelection(i);
                            FixSpinner.forc_disable = false;
                            break;
                        }
                    }
//                    viewmodel.plc = object.getString("plc");
                }
                if (object.has("function")) {
                    viewmodel.function = object.getString("function");
                }
                if (object.has("datablockcount")) {
                    viewmodel.datablockcount = object.getString("datablockcount");
                }
                if (object.has("description")) {
                    viewmodel.description = object.getString("description");
                }

                //fill parameter
                if (object.getInt("functionblock_id") != 0) {// its share db
                    I4AllSetting parameter = null;
                    List<I4AllSetting> paramslist = db.getitembyitesref(object.getInt("functionblock_id"));
                    for (I4AllSetting item : paramslist) {
                        JSONObject fun = new JSONObject(item.getItemsData());
                        if (fun.getInt("parameterid") == object.getInt("parameter_id")) {
                            parameter = item;
                            break;
                        }
                    }
                    if (parameter != null) {
                        fill_parameter(parameter);
                    }
                }else{
                    binding.edtname.setText(object.getString("name"));
                    viewmodel.name = object.getString("name");
                    binding.edtaddress.setText(object.getString("address"));
                    viewmodel.address = object.getString("address");
                    binding.edtbitnumber.setText(object.getString("bitnumber"));
                    viewmodel.bitnumber = object.getString("bitnumber");
                    binding.edtparameterid.setText(object.getString("parameterid"));
                    viewmodel.parameterid = object.getString("parameterid");
                    binding.edtparamdescription.setText(object.getString("description"));
                    viewmodel.description = object.getString("description");
                    viewmodel.datablockparameter = object.getString("parameterid");
                    JSONObject obj = new JSONObject(item.getItemsData());
                    viewmodel.functionblocknumber = String.valueOf(obj.getString("functionblock_id"));
                    binding.edtname.setEnabled(true);
                    binding.edtaddress.setEnabled(true);
                    binding.edtbitnumber.setEnabled(true);
                    binding.sptype.setEnabled(true);
                    binding.edtparameterid.setEnabled(true);
                    binding.edtparamdescription.setEnabled(true);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            update = false;
        }

        main = this;
    }


    public void fill_parameter(I4AllSetting selected) throws JSONException {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                JSONObject object = null;
                try {
                    object = new JSONObject(selected.getItemsData());
                    binding.edtname.setText(object.getString("name"));
                    viewmodel.name = object.getString("name");
                    binding.edtaddress.setText(object.getString("address"));
                    viewmodel.address = object.getString("address");
                    binding.edtbitnumber.setText(object.getString("bitnumber"));
                    viewmodel.bitnumber = object.getString("bitnumber");
                    binding.edtparameterid.setText(object.getString("parameterid"));
                    viewmodel.parameterid = object.getString("parameterid");
                    binding.edtparamdescription.setText(object.getString("description"));
                    viewmodel.description = object.getString("description");
                    viewmodel.datablockparameter = object.getString("parameterid");
                    if (update){
                        JSONObject obj = new JSONObject(item.getItemsData());
                        viewmodel.functionblocknumber = String.valueOf(obj.getString("functionblock_id"));
                    }else{
                        viewmodel.functionblocknumber = String.valueOf(selected.getItemsRef());
                    }
                    binding.edtname.setEnabled(false);
                    binding.edtaddress.setEnabled(false);
                    binding.edtbitnumber.setEnabled(false);
                    binding.sptype.setEnabled(false);
                    binding.edtparameterid.setEnabled(false);
                    binding.edtparamdescription.setEnabled(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}