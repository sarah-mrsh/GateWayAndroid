package com.example.fanp.presentation.mqtt.broker.topic;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;

import java.util.ArrayList;

public class AdapterTopic extends RecyclerView.Adapter<AdapterTopic.ViewHoldertopic> {


    ArrayList<topicmodel> data = new ArrayList<>();
    Context context;
    TopicAdapter listener;

    public AdapterTopic(ArrayList<topicmodel> data, Context context, TopicAdapter listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHoldertopic onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_topic, parent, false);
        return new ViewHoldertopic(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldertopic holder, int position) {
        topicmodel model = data.get(position);
        holder.clientname.setText(model.clientname);
        holder.name.setText(model.name);
        switch (model.qos){
            case "0":
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        holder.qos.setSelection(0);
                    }
                }, 100);
                break;
            case "1":
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        holder.qos.setSelection(1);
                    }
                }, 100);
                break;
            case "2":
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        holder.qos.setSelection(2);
                    }
                }, 100);
                break;
        }
        holder.retain.setChecked(model.retain);
        holder.privaited.setChecked(model.privated);


        holder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.addrow();
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


    public static class ViewHoldertopic extends RecyclerView.ViewHolder {
        EditText clientname,name;
        ImageView img_add;
        Spinner qos;
        CheckBox retain,privaited;
        public ViewHoldertopic(@NonNull View itemView) {
            super(itemView);
            name = (EditText)itemView.findViewById(R.id.name);
            clientname = (EditText)itemView.findViewById(R.id.clientname);
            img_add = (ImageView) itemView.findViewById(R.id.imgadd);
            retain = (CheckBox) itemView.findViewById(R.id.retain);
            privaited = (CheckBox) itemView.findViewById(R.id.chbprivaite);
            qos = (Spinner) itemView.findViewById(R.id.qos);
//            type = (TextView)itemView.findViewById(R.id.type);
//            topic = (TextView)itemView.findViewById(R.id.topicname);
//            remove = (TextView)itemView.findViewById(R.id.remove);
//            edit = (TextView)itemView.findViewById(R.id.edit);
        }

    }
}
