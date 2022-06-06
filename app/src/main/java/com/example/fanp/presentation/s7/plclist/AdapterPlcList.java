package com.example.fanp.presentation.s7.plclist;

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
import com.example.fanp.presentation.modbus.rtu.serverlistrtu.AdapterServerRtu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterPlcList extends RecyclerView.Adapter<AdapterPlcList.ViewHolder> {


    Context ctx;
    PlcListImp listener;
    List<I4AllSetting> main;


    public AdapterPlcList(Context ctx, PlcListImp listener, List<I4AllSetting> main) {
        this.ctx = ctx;
        this.listener = listener;
        this.main = main;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_plc_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        I4AllSetting model = main.get(position);

        try {
            JSONObject item = new JSONObject(model.getItemsData());
            holder.devicename.setText(item.getString("devicename"));
            holder.deviceid.setText(item.getString("deviceid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.imfdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_confirmation(main.get(position));
//                listener.delete(main.get(position));
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
