package com.example.fanp.presentation.mqtt.broker.clientlist;

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

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {


    ArrayList<clientmodel> data = new ArrayList<>();
    Context context;
    AdapterClientImp listener;

    public ClientAdapter(ArrayList<clientmodel> data, Context context, AdapterClientImp listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_clientlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        clientmodel model = data.get(position);


        holder.name.setText(model.name);
        holder.qos.setText(model.qos);
        holder.id.setText(model.id);

        holder.topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.topic(model);
            }
        });
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
        TextView id,qos,name,remove,edit,topic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txtname);
            qos = (TextView)itemView.findViewById(R.id.txtqos);
            id = (TextView)itemView.findViewById(R.id.txtid);
            remove = (TextView)itemView.findViewById(R.id.remove);
            edit = (TextView)itemView.findViewById(R.id.edit);
            topic = (TextView)itemView.findViewById(R.id.topic);

        }

    }
}
