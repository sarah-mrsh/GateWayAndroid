package com.example.fanp.presentation.node.add;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.MqttTaglistImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterTags extends RecyclerView.Adapter<AdapterTags.ViewHolder> {


    List<NodeTagModel> data = new ArrayList<>();
    List<String> tags = new ArrayList<>();
    Context context;
    tagImp listener;

    public AdapterTags(List<NodeTagModel> data, Context context,tagImp listener,List<String> tags) {
        this.tags=tags;
        this.listener=listener;
        this.data = data;
        this.context = context;





    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_tag_node, parent, false);
        return new ViewHolder(view);
    }





    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        NodeTagModel model = data.get(position);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context , android.R.layout.simple_spinner_item, tags);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        holder.sptag.setAdapter(adapter);





        holder.edtname.setText(model.alternative);

        for (int i=0;i<tags.size();i++){
            if (model.tag.equals(tags.get(i))){
                holder.sptag.setSelection(i);
                break;
            }
        }
        switch (model.mode){
            case "Public":{
                holder.spmode.setSelection(0);
                break;
            }
            case "Private":{
                holder.spmode.setSelection(1);
                break;
            }
        }


        if (position+1!=data.size()){
            holder.imgadd.setVisibility(View.INVISIBLE);
        }
        holder.imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                notifyItemInserted(position);
                listener.temp_save();
                data.add(new NodeTagModel("","",""));
            }
        });
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyItemRemoved(position);
                listener.tmep_save_remove();
            }
        });




        holder.sptag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                holder.edtname.setText(holder.sptag.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }







    @Override
    public int getItemCount() {
        return data.size();
    }








    public class ViewHolder extends RecyclerView.ViewHolder {

        EditText edtname;
        Spinner spmode,sptag;
        ImageView imgadd,imgdelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edtname = (EditText) itemView.findViewById(R.id.edtname);
            spmode = (Spinner) itemView.findViewById(R.id.spmode);
            sptag = (Spinner) itemView.findViewById(R.id.sptag);
            imgadd = (ImageView) itemView.findViewById(R.id.imgadd);
            imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
        }

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
