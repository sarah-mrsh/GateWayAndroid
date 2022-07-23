package com.example.fanp.presentation.s7.tag.datablock;

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
import com.example.fanp.presentation.s7.plclist.PlcListImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterDatablock extends RecyclerView.Adapter<AdapterDatablock.ViewHolder> {


    Context ctx;
    DatablockImp listener;
    List<I4AllSetting> main;


    public AdapterDatablock(Context ctx, DatablockImp listener, List<I4AllSetting> main) {
        this.ctx = ctx;
        this.listener = listener;
        this.main = main;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_datablock_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        I4AllSetting model = main.get(position);


//        public String tagname;
//        public String tagid;
//        public String plc;
//        public String function;
//        public String datablockcount;
//        public String description;


        try {
            JSONObject item = new JSONObject(model.getItemsData());
            holder.name.setText(item.getString("tagname"));
            holder.tagid.setText(item.getString("tagid"));
            holder.plcid.setText(item.getString("functionblocknumber"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.edit(model);
            }
        });
        holder.imfdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.delete(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return main.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,tagid,plcid;
        ImageView imfdelete,imgedit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            tagid=(TextView)itemView.findViewById(R.id.tagid);
            plcid=(TextView)itemView.findViewById(R.id.plcid);

            imfdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
//            imgtopic = (ImageView) itemView.findViewById(R.id.imgtopic);
        }
    }
}
