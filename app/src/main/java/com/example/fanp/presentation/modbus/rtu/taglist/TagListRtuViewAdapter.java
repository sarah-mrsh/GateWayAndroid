package com.example.fanp.presentation.modbus.rtu.taglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.AdapterServerRtu;
import com.example.fanp.presentation.modbus.tcp.serverlist.ServerListAdaptertCP;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TagListRtuViewAdapter extends RecyclerView.Adapter<TagListRtuViewAdapter.ViewHolder> {


    List<I4AllSetting> data = new ArrayList<>();
    Context context;

    public TagListRtuViewAdapter(List<I4AllSetting> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_taglist_rtu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject object = new JSONObject(data.get(position).getItemsData());
            holder.name.setText(object.getString("tagname"));
            holder.id.setText(object.getString("tagid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tagname);
            id = (TextView) itemView.findViewById(R.id.tagid);
        }
    }
}
