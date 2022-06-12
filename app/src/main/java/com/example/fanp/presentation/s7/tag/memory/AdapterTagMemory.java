package com.example.fanp.presentation.s7.tag.memory;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.s7.plclist.PlcListImp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterTagMemory extends RecyclerView.Adapter<AdapterTagMemory.ViewHolder> {


    Context ctx;
    TagmemorylistImpl listener;
    List<I4AllSetting> main;


    public AdapterTagMemory(Context ctx, TagmemorylistImpl listener, List<I4AllSetting> main) {
        this.ctx = ctx;
        this.listener = listener;
        this.main = main;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_tag_memory_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        I4AllSetting model = main.get(position);

//        object.put("tagname", tagname);
//        object.put("tagid", tagid);
//        object.put("plc", plc);
//        object.put("type", type);
//        object.put("address", address);
//        object.put("bitnumber", bitnumber);
//        object.put("function", function);
//        object.put("description", description);

        try {
            JSONObject item = new JSONObject(model.getItemsData());
//            holder.devicename.setText(item.getString("devicename"));
//            holder.deviceid.setText(item.getString("deviceid"));
            holder.name.setText(item.getString("tagname"));
            holder.tagid.setText(item.getString("tagid"));
            holder.plcid.setText(item.getString("plc"));
        } catch (JSONException e) {
//            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        holder.imfdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_confirmation(main.get(position));

            }
        });
        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.edit(model);
            }
        });


    }


    public void delete_confirmation(I4AllSetting item){
        Dialog dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.delete_dialog);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button bt_delete = (Button) dialog.findViewById(R.id.bt_delete);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.delete(item);
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
        return main.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,tagid,plcid;
        ImageView imfdelete,imgedit,imgtopic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            tagid=(TextView)itemView.findViewById(R.id.tagid);
            plcid=(TextView)itemView.findViewById(R.id.plcid);

            imfdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
        }
    }
}
