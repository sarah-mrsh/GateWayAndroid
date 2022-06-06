package com.example.fanp.presentation.s7.tag.io;

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
import com.example.fanp.presentation.s7.tag.datablock.DatablockImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterIOtaglist extends RecyclerView.Adapter<AdapterIOtaglist.ViewHolder> {


    Context ctx;
    ioImp listener;
    List<I4AllSetting> main;


    public AdapterIOtaglist(Context ctx, ioImp listener, List<I4AllSetting> main) {
        this.ctx = ctx;
        this.listener = listener;
        this.main = main;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_io_list, parent, false);
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
            holder.plcid.setText(item.getString("plc"));
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

        TextView name,tagid,tagtype,writable,plcid;
        ImageView imfdelete,imgedit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            tagid=(TextView)itemView.findViewById(R.id.tagid);
            tagtype=(TextView)itemView.findViewById(R.id.tagtype);
            writable=(TextView)itemView.findViewById(R.id.writable);
            plcid=(TextView)itemView.findViewById(R.id.plcid);

            imfdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
//            imgtopic = (ImageView) itemView.findViewById(R.id.imgtopic);
        }
    }
}
