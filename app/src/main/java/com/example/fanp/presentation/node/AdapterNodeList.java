package com.example.fanp.presentation.node;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.mqtt.clientmqtt.taglist.MqttTaglistImp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterNodeList extends RecyclerView.Adapter<AdapterNodeList.ViewHolder> {


    List<I4AllSetting> data = new ArrayList<>();
    Context context;


    public AdapterNodeList(List<I4AllSetting> data, Context context) {
        this.data = data;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_node_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        I4AllSetting model = data.get(position);

        try {
            JSONArray data = new JSONArray(model.getItemsData());
            JSONObject object = data.getJSONObject(0);
            holder.name.setText(object.getString("name"));
            holder.type.setText(object.getString("type"));
            holder.destination.setText(object.getString("destination"));
            JSONArray tags = data.getJSONArray(1);
            holder.tagcount.setText(String.valueOf(tags.length()));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void delete_confirmation(I4AllSetting item){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.delete_dialog);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button bt_delete = (Button) dialog.findViewById(R.id.bt_delete);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                listener.delete(item);
                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, destination,tagcount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.type);
            name = (TextView) itemView.findViewById(R.id.name);
            destination = (TextView) itemView.findViewById(R.id.destination);
            tagcount = (TextView) itemView.findViewById(R.id.tagcount);
        }

    }
}
