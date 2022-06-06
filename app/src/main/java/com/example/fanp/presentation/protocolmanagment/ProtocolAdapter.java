package com.example.fanp.presentation.protocolmanagment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fanp.R;
import com.example.fanp.domain.entity.ProtocolTypeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ProtocolAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<Image> items = new ArrayList<>();



    private Context ctx;

    private List<ProtocolTypeEntity> protocolList;
    //final variable
    private Activity activity;





    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView protocoltype;

        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            //name = (TextView) v.findViewById(R.id.name);
            protocoltype = (TextView) v.findViewById(R.id.protocoltype);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_protocol, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
//        final PrefManager prf = new PrefManager(activity.getApplicationContext());
//        //Bitmap obj =  StringToBitMap(protocolList.get(position).getprotocolImage());
//        if (holder instanceof ProtocolAdapter.OriginalViewHolder) {
//            ProtocolAdapter.OriginalViewHolder view = (ProtocolAdapter.OriginalViewHolder) holder;
//            switch (protocolList.get(position).getprotocolId())
//            {
//                case 1:
//                    view.protocoltype.setText("MODBUS");
//                    view.image.setImageResource(R.drawable.modbustcp);
//                    //  view.name.setText("مدیریت پروتکل MODBUS");
//                    break;
//                case 2:
//                    view.protocoltype.setText("SNMP");
//                    view.image.setImageResource(R.drawable.snmpprotocol);
//                    //  view.name.setText("مدیریت پروتکل SNMP");
//                    break;
////                case 3:
////                    view.protocoltype.setText("SERIAL");
////                    view.image.setImageResource(R.drawable.serial);
////                    view.name.setText("مدیریت پروتکل SERIAL");
////                    break;
//                case 4:
//                    view.protocoltype.setText("MQTT");
//                    view.image.setImageResource(R.drawable.mqtt);
//                    //   view.name.setText("مدیریت پروتکل MQTT");
//                    break;
//                case 5:
//                    view.protocoltype.setText("S7");
//                    view.image.setImageResource(R.drawable.mqtt);
//                    //   view.name.setText("مدیریت پروتکل S7");
//                    break;
//                case 6:
//                    view.protocoltype.setText("COAP");
//                    view.image.setImageResource(R.drawable.mqtt);
//                    //   view.name.setText("مدیریت پروتکل COAP");
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + protocolList.get(position).getprotocolId());
//            }
//            view.image.setVisibility(View.VISIBLE);
//            Typeface face = Typeface.createFromAsset(ctx.getAssets(), "BYekan.ttf");
//            // view.name.setTypeface(face);
//            view.protocoltype.setTypeface(face);
//
//            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    showCheckyYesNo(position);
//                }
//            });
            //notifyDataSetChanged();
//        }
//        notifyDataSetChanged();
    }
//    private void DeleteProtocol(int position)
//    {
//        Intent intent = null;
//        String Clicked = protocolList.get(position).getprotocolName();
//        DBHandler dbHandler;
//        dbHandler = new DBHandler(activity.getApplicationContext());
//        I4AllSetting i4AllSettingFromDB = dbHandler.getI4AllSettingByRefId(AllSettingIDs.PROTOCOLSTYPE.getValue());
//        Gson gson = new Gson();
//        ProtocolsType protocolsType = gson.fromJson(i4AllSettingFromDB.getItemsData(),ProtocolsType.class);
//        switch (Clicked)
//        {
//            case "MODBUS":
//                protocolsType.setModbus(false);
//                break;
//            case "SNMP":
//                protocolsType.setSNMP(false);
//                break;
////            case "SERIAL":
////                protocolsType.setSERIAL(false);
////                break;
//            case "COAP":
//                protocolsType.setCOAP(false);
//                break;
//            case "S7":
//                protocolsType.setS7(false);
//                break;
//            case "MQTT":
//                protocolsType.setMQTT(false);
//                break;
//        }
//        Log.v("ads","is not loaded");
////                    activity.startActivity(intent);
////                    activity.overridePendingTransition(R.anim.enter, R.anim.exit);
//        Utility utility = new Utility();
//        I4AllSetting i4AllSettingProtocolsAdd;
//        Gson gsonProtocolsType = new Gson();
//        String stringProtocolsTypes = gsonProtocolsType.toJson(protocolsType);
//        i4AllSettingProtocolsAdd = new I4AllSetting(400, AllSettingIDs.PROTOCOLSTYPE.getValue(), stringProtocolsTypes, false, utility.getCurrentTime());
//        dbHandler.updateI4AllSetting(i4AllSettingProtocolsAdd);
//
//        protocolList.remove(position);
//        ((MProtocolActivity) activity).fillProtocolList();
//        notifyDataSetChanged();
//
//    }
    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    @Override
    public int getItemCount() {
        return protocolList.size();
    }


//    private void showCheckyYesNo(final int position) {
//        final Dialog dialog = new Dialog(activity);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
//        dialog.setContentView(R.layout.dialoge_check_yes_no);
//        dialog.setCancelable(true);
//
//        boolean returnValue=false;
//        Typeface face = Typeface.createFromAsset(activity.getAssets(), "BYekan.ttf");
//        ((TextView) dialog.findViewById(R.id.title_sent_request)).setText("حذف پروتکل");
//        ((TextView) dialog.findViewById(R.id.title_sent_request)).setTypeface(face);
//
//        ((TextView) dialog.findViewById(R.id.text_sent_request)).setText("آیا میخواهید پروتکل مورد نظر از سیستم حذف گردد ؟ (" + protocolList.get(position).getprotocolName() + ")");
//        ((TextView) dialog.findViewById(R.id.text_sent_request)).setTypeface(face);
//        ((Button) dialog.findViewById(R.id.bt_dialog_yes)).setTypeface(face);
//        ((Button) dialog.findViewById(R.id.bt_dialog_no)).setTypeface(face);
//        ((Button) dialog.findViewById(R.id.bt_dialog_yes)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                DeleteProtocol(position);
//            }
//        });
//
//        ((Button) dialog.findViewById(R.id.bt_dialog_no)).setTypeface(face);
//        ((Button) dialog.findViewById(R.id.bt_dialog_no)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

//    public boolean check(){
//        PrefManager prf = new PrefManager(activity.getApplicationContext());
//        if (activity.getString(R.string.AD_MOB_ENABLED_INTERSTITAL).equals("false")){
//            return false;
//        }
//        if (!prf.getString("SUBSCRIBED").equals("FALSE")) {
//            return false;
//        }
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String strDate = sdf.format(c.getTime());
//
//        if (prf.getString("LAST_DATE_ADS").equals("")) {
//            prf.setString("LAST_DATE_ADS", strDate);
//        } else {
//            String toyBornTime = prf.getString("LAST_DATE_ADS");
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//            try {
//                Date oldDate = dateFormat.parse(toyBornTime);
//                System.out.println(oldDate);
//
//                Date currentDate = new Date();
//
//                long diff = currentDate.getTime() - oldDate.getTime();
//                long seconds = diff / 1000;
//
//                if (seconds > Integer.parseInt(activity.getResources().getString(R.string.AD_MOB_INTERSTITAL_TIME))) {
//                    prf.setString("LAST_DATE_ADS", strDate);
//                    return  true;
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return  false;
//    }
}