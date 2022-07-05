package com.example.fanp.presentation.wifi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.fanp.R;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String wifiList[];
    int icons[];
    LayoutInflater inflter;


    public CustomAdapter(Context applicationContext, String[] wifiList, int[] icons) {
        this.context = context;
        this.wifiList = wifiList;
        this.icons = icons;
        inflter = (LayoutInflater.from(applicationContext));
    }

    // Create new views (invoked by the layout manager)

    @Override
    public int getCount() {
        return wifiList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row_custom_adapter,null);
        TextView country = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        country.setText(wifiList[i]);
        icon.setImageResource(icons[i]);
        //bbb
        return view;
    }

}