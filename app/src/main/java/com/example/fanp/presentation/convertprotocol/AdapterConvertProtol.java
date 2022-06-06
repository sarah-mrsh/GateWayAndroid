package com.example.fanp.presentation.convertprotocol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterConvertProtol extends RecyclerView.Adapter<AdapterConvertProtol.ViewHolder> {


    I4AllSettingDao db;
    List<TagModelSP> spdata = new ArrayList<>();
    List<String> names = new ArrayList<>();
    List<I4AllSetting> data = new ArrayList<>();


    public void fillnames() {
        for (TagModelSP item : spdata) {
            names.add(item.getName());
        }
    }

    Context context;

    public AdapterConvertProtol(List<TagModelSP> spdata, Context context, List<I4AllSetting> data,I4AllSettingDao db) {
        this.data = data;
        this.spdata = spdata;
        this.context = context;
        this.db=db;
        fillnames();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_convert_protocol, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.sptar.setAdapter(adapter);

        holder.spdest.setAdapter(adapter);
        try {
            JSONObject object = new JSONObject(data.get(position).getItemsData());
            for (int i = 0; i < spdata.size(); i++) {
                if (spdata.get(i).name.equals(object.getString("from"))) {
                    holder.sptar.setSelection(i);
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject object = new JSONObject(data.get(position).getItemsData());
            for (int i = 0; i < spdata.size(); i++) {
                if (spdata.get(i).name.equals(object.getString("to"))) {
                    holder.spdest.setSelection(i);
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size()>position){
                    db.delete(data.get(position));
                    data.remove(position);
                    notifyItemRemoved(position);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        Spinner sptar, spdest;
        TextView txtdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sptar = (Spinner) itemView.findViewById(R.id.sptar);
            spdest = (Spinner) itemView.findViewById(R.id.spdest);
            txtdelete = (TextView) itemView.findViewById(R.id.txtdelete);

        }
    }
}
