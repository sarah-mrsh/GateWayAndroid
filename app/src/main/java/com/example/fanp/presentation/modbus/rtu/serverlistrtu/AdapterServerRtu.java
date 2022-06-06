package com.example.fanp.presentation.modbus.rtu.serverlistrtu;

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
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerListAdaptertCP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdapterServerRtu extends RecyclerView.Adapter<AdapterServerRtu.ViewHolder> {


    JSONArray data = new JSONArray();
    I4AllSetting main;
    Context ctx;
    ServerImp listener;

    public AdapterServerRtu(I4AllSetting data, Context ctx, ServerImp listener) {
        this.main = data;
        try {
            this.data = new JSONArray(data.getItemsData());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.ctx = ctx;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_server_rtu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject object = data.getJSONObject(position);
            holder.name.setText(object.getString("devicename"));
            holder.deviceid.setText(object.getString("deviceid"));
            holder.modbusadress.setText(object.getString("modbusadress"));
            holder.imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.delete(main,position);
                }
            });
            holder.imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.edit(main,position);
                }
            });
            holder.imgtopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.taglist(main,position);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,deviceid,modbusadress;
        ImageView imgedit,imgdelete,imgtopic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            deviceid = (TextView) itemView.findViewById(R.id.deviceid);
            modbusadress = (TextView) itemView.findViewById(R.id.modbusadress);

            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
            imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            imgtopic = (ImageView) itemView.findViewById(R.id.imgtopic);

        }
    }
}
