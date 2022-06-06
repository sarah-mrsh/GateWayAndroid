package com.example.fanp.presentation.mqtt.mainpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.presentation.mqtt.presenter.BrokerImp;

import java.util.List;

public class BrokersListAdapter extends RecyclerView.Adapter<BrokersListAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private BrokerImp listener;
    private Context context;
    // data is passed into the constructor


    public BrokersListAdapter(List<String> mData, BrokerImp listener,Context ctx) {
        this.context=ctx;
        this.mData = mData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_brokers_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtsample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.test(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtsample;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsample = (TextView)itemView.findViewById(R.id.txtBrokersName);

        }
    }
}