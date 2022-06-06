package com.example.fanp.presentation.mqtt.broker.taglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;

import org.json.JSONException;

import java.util.ArrayList;

public class AdapterTagList extends RecyclerView.Adapter<AdapterTagList.ViewHolder> {


    ArrayList<tagmodel> data = new ArrayList<>();
    Context context;
    AdapterImp listener;

    public AdapterTagList(ArrayList<tagmodel> data, Context context, AdapterImp listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_ta_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        tagmodel model = data.get(position);

        holder.name.setText(model.name);
        holder.type.setText(model.type);
        holder.topic.setText(model.topic);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.Delete(model);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    listener.Edit(model);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type,name,topic,remove,edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = (TextView)itemView.findViewById(R.id.type);
            name = (TextView)itemView.findViewById(R.id.tagname);
            topic = (TextView)itemView.findViewById(R.id.topicname);
            remove = (TextView)itemView.findViewById(R.id.remove);
            edit = (TextView)itemView.findViewById(R.id.edit);
        }

    }
}
