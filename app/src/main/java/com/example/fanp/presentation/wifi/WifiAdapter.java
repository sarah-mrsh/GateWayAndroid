package com.example.fanp.presentation.wifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.local.repository.WifiSetting;

import java.util.ArrayList;
import java.util.List;

public class WifiAdapter extends RecyclerView.Adapter<WifiAdapter.ViewHolder> {

    int icons[] = {R.drawable.wifi4, R.drawable.wifi4, R.drawable.wifi3, R.drawable.wifi2, R.drawable.wifi1};

    ArrayList<wifi> data = new ArrayList<>();
    Context context;
    AdapterWifiImpl listener;
    List<WifiSetting> saved;






    public WifiAdapter(ArrayList<wifi> data, Context context, AdapterWifiImpl listener, List<WifiSetting> saved) {

        this.data = data;
        this.context = context;
        this.listener = listener;
        this.saved = saved;
    }


    public boolean isit(String name){
        for (WifiSetting item : saved){
            if (item.getNemtworkName().equals(name)){
                return true;
            }
        }
        return false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_wifi_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        wifi model = data.get(position);


        if (model.rate<20){
            holder.imgrate.setImageResource(icons[0]);
        }else if (model.rate>20 & model.rate<40){
            holder.imgrate.setImageResource(icons[1]);
        }else if (model.rate<60&model.rate>40){
            holder.imgrate.setImageResource(icons[2]);
        }else if (model.rate<80&model.rate>60){
            holder.imgrate.setImageResource(icons[3]);
        }else if (model.rate>80){
            holder.imgrate.setImageResource(icons[4]);
        }

        holder.lin_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.connect(model);
            }
        });
        holder.wifiname.setText(model.name);


        if (model.isStatus()){
            holder.btnconnected.setVisibility(View.VISIBLE);
            holder.imgsettingwifi.setVisibility(View.VISIBLE);

        }

        holder.imgsettingwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.get_wifi_config();
            }
        });

        holder.btnconnected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.disconnect(model.name);
            }
        });

        if (isit(model.name)){
            holder.wifiname.setTextColor(context.getResources().getColor(R.color.main_yellow));
        }

//        holder.ssid.setText(ssidList.get(position));
//        holder.icon.setImageResource(flags.get(position));
//        holder.connect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    listener.Connect(model);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView ssid;
//        ImageView icon;
//        Button connect;
        LinearLayout lin_wifi;
        TextView wifiname;
        ImageView imgrate;
        ImageView imgsettingwifi;
        Button btnconnected;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lin_wifi = (LinearLayout) itemView.findViewById(R.id.lin_wifi);
            wifiname = (TextView)itemView.findViewById(R.id.wifiname);
            imgrate = (ImageView) itemView.findViewById(R.id.imgrate);
            imgsettingwifi = (ImageView) itemView.findViewById(R.id.imgsettingwifi);
            btnconnected = (Button) itemView.findViewById(R.id.btnconnected);
        //            icon = (ImageView)itemView.findViewById(R.id.imageBitrate);
//            connect = (Button) itemView.findViewById(R.id.connect);
//            ssid = (TextView)itemView.findViewById(R.id.ssid);
        }

    }
}
