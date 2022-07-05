package com.example.fanp.presentation.wifi.savednetworks;

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
import com.example.fanp.presentation.wifi.AdapterWifiImpl;
import com.example.fanp.presentation.wifi.wifi;

import java.util.ArrayList;
import java.util.List;

public class SavedWifiAdapter extends RecyclerView.Adapter<SavedWifiAdapter.ViewHolder> {

//    int icons[] = {R.drawable.wifi4, R.drawable.wifi4, R.drawable.wifi3, R.drawable.wifi2, R.drawable.wifi1};

    List<WifiSetting> data = new ArrayList<>();
    Context context;
    SavedImp listener;

    public SavedWifiAdapter(List<WifiSetting> data, Context context, SavedImp listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row_saved_network, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WifiSetting model = data.get(position);

        holder.wifiname.setText(model.getNemtworkName());
        holder.btnconnected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.forget(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        LinearLayout lin_wifi;
        TextView wifiname;
//        ImageView imgrate;
//        ImageView imgsettingwifi;
        Button btnconnected;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            lin_wifi = (LinearLayout) itemView.findViewById(R.id.lin_wifi);
            wifiname = (TextView)itemView.findViewById(R.id.wifiname);
//            imgrate = (ImageView) itemView.findViewById(R.id.imgrate);
//            imgsettingwifi = (ImageView) itemView.findViewById(R.id.imgsettingwifi);
            btnconnected = (Button) itemView.findViewById(R.id.btnconnected);
        }

    }
}
