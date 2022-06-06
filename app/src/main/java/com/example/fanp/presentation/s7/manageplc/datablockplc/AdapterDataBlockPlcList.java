package com.example.fanp.presentation.s7.manageplc.datablockplc;

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
import com.example.fanp.presentation.s7.plclist.PlcListImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterDataBlockPlcList extends RecyclerView.Adapter<AdapterDataBlockPlcList.ViewHolder> {


    Context ctx;
    PlcListImp listener;
    List<I4AllSetting> main;


    public AdapterDataBlockPlcList(Context ctx, PlcListImp listener, List<I4AllSetting> main) {
        this.ctx = ctx;
        this.listener = listener;
        this.main = main;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_datablock_plc_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        I4AllSetting model = main.get(position);


    }


    @Override
    public int getItemCount() {
        return main.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView devicename,deviceid;
        ImageView imfdelete,imgedit,imgtopic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceid=(TextView)itemView.findViewById(R.id.deviceid);
            devicename=(TextView)itemView.findViewById(R.id.devicename);

            imfdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
            imgtopic = (ImageView) itemView.findViewById(R.id.imgtopic);
        }
    }
}
