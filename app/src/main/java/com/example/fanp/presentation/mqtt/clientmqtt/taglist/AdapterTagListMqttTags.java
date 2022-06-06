package com.example.fanp.presentation.mqtt.clientmqtt.taglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.I4AllSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdapterTagListMqttTags extends RecyclerView.Adapter<AdapterTagListMqttTags.ViewHolder> {


    List<I4AllSetting> data = new ArrayList<>();
    Context context;
    MqttTaglistImp listener;

    public AdapterTagListMqttTags(List<I4AllSetting> data, Context context, MqttTaglistImp listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_tag_mqtt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        I4AllSetting model = data.get(position);

//        holder.name.setText(model.getName());
//        holder.type.setText(model.getType());
//        holder.topic.setText(model.topic);

        try {
            JSONObject jsonObject = new JSONObject(model.getItemsData());
            holder.name.setText(jsonObject.getString("tagname"));
            holder.topic.setText(jsonObject.getString("topicname"));
            holder.type.setText(jsonObject.getString("type"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.delete(model);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.update(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView type, name, topic, remove, edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.type);
            name = (TextView) itemView.findViewById(R.id.tagname);
            topic = (TextView) itemView.findViewById(R.id.topicname);
            remove = (TextView) itemView.findViewById(R.id.remove);
            edit = (TextView) itemView.findViewById(R.id.edit);
        }

    }
}
