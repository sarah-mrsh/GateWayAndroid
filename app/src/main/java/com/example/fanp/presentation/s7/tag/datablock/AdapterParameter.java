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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterParameter extends RecyclerView.Adapter<AdapterParameter.ViewHolder> {


    Context ctx;
    ParameterImpl listener;
    List<I4AllSetting> main;


    public AdapterParameter(Context ctx, ParameterImpl listener, List<I4AllSetting> main) {
        this.ctx = ctx;
        this.listener = listener;
        this.main = main;
    }

    @NonNull
    @Override
    public AdapterParameter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_parameter, parent, false);
        return new AdapterParameter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterParameter.ViewHolder holder, int position) {
        I4AllSetting model = main.get(position);
        try {

            JSONObject item = new JSONObject(model.getItemsData());
            holder.name.setText(item.getString("name"));

            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectParameter(model);
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

        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.txtparametername);

        }
    }
}
