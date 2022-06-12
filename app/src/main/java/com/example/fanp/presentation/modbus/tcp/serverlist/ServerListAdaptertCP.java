package com.example.fanp.presentation.modbus.tcp.serverlist;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerListAdaptertCP extends RecyclerView.Adapter<ServerListAdaptertCP.ViewHolder> {



    JSONArray data = new JSONArray();
    Context ctx;
    I4AllSetting main;
    ServerTcpIml listener;

    public ServerListAdaptertCP(JSONArray data, Context ctx, I4AllSetting main, ServerTcpIml listener) {
        this.data = data;
        this.ctx = ctx;
        this.main = main;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_server_tcp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            JSONObject object = data.getJSONObject(position);
            holder.name.setText(object.getString("devicename"));
            holder.deviceid.setText(object.getString("deviceid"));
            holder.ip.setText(object.getString("ip"));
            holder.port.setText(object.getString("port"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.edit(main,position);
            }
        });

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_confirmation(main,position);
            }
        });
        holder.imgtopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.taglist(main,position);
            }
        });
    }
    public void delete_confirmation(I4AllSetting item,int position){
        Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.delete_dialog);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button bt_delete = (Button) dialog.findViewById(R.id.bt_delete);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.delete(item,position);
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
        return data.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,port,ip,deviceid;
        ImageView imgedit,imgdelete,imgtopic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            port = (TextView) itemView.findViewById(R.id.deviceport);
            ip = (TextView) itemView.findViewById(R.id.deviceip);
            deviceid = (TextView) itemView.findViewById(R.id.deviceid);


            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
            imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            imgtopic = (ImageView) itemView.findViewById(R.id.imgtopic);

        }
    }
}
