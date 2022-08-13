package com.example.fanp.presentation.business.add;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.fanp.domain.local.data.I4AllSettingDao;
import com.example.fanp.domain.local.repository.I4AllSetting;
import com.example.fanp.presentation.node.add.AdapterTags;
import com.example.fanp.utils.NameEdt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class AddBusinessViewModel extends ViewModel {


    public String name="";
    public String id="";
    public String trpersec="";
    public String size="";
    public boolean latestdata=true;

    public AddBusiness main;


    @Inject
    I4AllSettingDao db;


    @Inject
    public AddBusinessViewModel() {

    }

    public void savedata(NameEdt nameEdt) {
        
        if (!nameEdt.valid){
            Toast.makeText(main, "نام وارد نشده است", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (size.equals("") && trpersec.equals("")){
            Toast.makeText(main, "تعداد بسته در ثانیه یا حداکثر حجم هر بسته وارد نشده است", Toast.LENGTH_SHORT).show();
            return;
        }
        
        
        
        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
            object.put("id", id);
            object.put("trpersec", trpersec);
            object.put("size", size);
            object.put("latestdata", latestdata);


            I4AllSetting data = null;

            List<I4AllSetting> businesses = db.get_businesses();
            for (I4AllSetting item : businesses) {
                JSONObject obj = new JSONObject(item.getItemsData());
                if (obj.getString("id").equals(id)) {//need to update
                    data = item;
                    break;
                }
            }
            if (data != null) {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                data.setDateTime(timeStamp);
                data.setItemsData(object.toString());
                db.update(data);
            } else {

                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                db.insert(new I4AllSetting(0, 523, object.toString(), false, timeStamp));

            }
            main.finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        main.finish();
    }


    public void exit() {
        main.finish();
    }


}
