package com.example.fanp.presentation.mqtt.clientmqtt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerTcpIml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MqttClientListAdapter extends RecyclerView.Adapter<MqttClientListAdapter.ViewHolder> {



    Context ctx;
    List<I4AllSetting> main;
    mqttClientImp listener;


    public MqttClientListAdapter(Context ctx, List<I4AllSetting> main,mqttClientImp listener) {
        this.listener = listener;
        this.ctx = ctx;
        this.main = main;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_mqtt_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject object = new JSONObject(main.get(position).getItemsData());
            holder.brokername.setText(object.getString("clientname"));
            holder.address.setText(object.getString("ip"));
            holder.port.setText(object.getString("port"));
            holder.imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.delete(main.get(position));
                }
            });
            holder.imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.update(main.get(position));
                }
            });
            holder.imgtopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.taglist(main.get(position));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return main.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView brokername,port,address;
        ImageView imgedit,imgdelete,imgtopic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brokername = (TextView) itemView.findViewById(R.id.txtbrokername);
            port = (TextView) itemView.findViewById(R.id.txtport);
            address = (TextView) itemView.findViewById(R.id.txtaddress);


            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
            imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            imgtopic = (ImageView) itemView.findViewById(R.id.imgtopic);

        }
    }
}
