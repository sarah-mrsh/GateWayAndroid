package com.example.fanp.presentation.s7.manageplc.functionblock.parameters;

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
import com.example.fanp.presentation.s7.manageplc.datablockplc.ListDatablockplcImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AdapterPrameterFunctionBloc extends RecyclerView.Adapter<AdapterPrameterFunctionBloc.ViewHolder> {


    Context ctx;
    List<I4AllSetting> main;
    ListParameterImpl listener;

    public AdapterPrameterFunctionBloc(Context ctx, ListParameterImpl listener, List<I4AllSetting> main) {
        this.ctx = ctx;
        this.main = main;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater= LayoutInflater.from(ctx);
        View view = mInflater.inflate(R.layout.row_parameter_functionblock_plc_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        I4AllSetting model = main.get(position);

        try {
            JSONObject object = new JSONObject(model.getItemsData());
            holder.txtparametername.setText(object.getString("name"));
            holder.parametertype.setText(object.getString("functionblocknumber"));


            holder.imgedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.edit(model);
                }
            });
            holder.imfdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.delete(model);
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

        TextView txtparametername,parametertype,txtparameterbytecount,txtparameterBitNumber;
        ImageView imfdelete,imgedit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtparametername=(TextView)itemView.findViewById(R.id.txtparametername);
            parametertype=(TextView)itemView.findViewById(R.id.parametertype);
            txtparameterbytecount=(TextView)itemView.findViewById(R.id.txtparameterbytecount);
            txtparameterBitNumber=(TextView)itemView.findViewById(R.id.txtparameterBitNumber);

            imfdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            imgedit = (ImageView) itemView.findViewById(R.id.imgedit);
        }
    }
}
