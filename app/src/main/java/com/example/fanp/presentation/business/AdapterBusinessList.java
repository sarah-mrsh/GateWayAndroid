package com.example.fanp.presentation.business;

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
import com.example.fanp.presentation.s7.manageplc.functionblock.ListFunctionBLockImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterBusinessList extends RecyclerView.Adapter<AdapterBusinessList.ViewHolder> {


    Context ctx;
    List<I4AllSetting> main;
    BusinessListImpl listener;

    public AdapterBusinessList(Context ctx, List<I4AllSetting> main,BusinessListImpl listener) {
        this.ctx = ctx;
        this.main = main;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_business, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        I4AllSetting model = main.get(position);

        try {
            JSONObject data = new JSONObject(model.getItemsData());
            if (data.has("name")) {
                holder.name.setText(data.getString("name"));
            }
            if (data.has("id")) {
                holder.id.setText (data.getString("id"));
            }
            holder.imgdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.delete(model);
                }
            });
            holder.imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.edit(model);
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

        TextView name,id;
        ImageView imgedit,imgdelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.txtname);
            id=(TextView)itemView.findViewById(R.id.txtid);

            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
            imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
        }
    }
}
