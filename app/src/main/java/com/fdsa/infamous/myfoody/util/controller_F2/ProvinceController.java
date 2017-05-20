package com.fdsa.infamous.myfoody.util.controller_F2;

import android.content.Context;

import com.fdsa.infamous.myfoody.common.bean_F2.ProvinceBean;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyGetMethod;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/23/2017.
 */

public class ProvinceController {
    private Context context;
    private String url;
    //Hàm khởi tạo
    public ProvinceController(Context context, String countryID, boolean isGetDistrict) {
        url = "api/province/get/" + countryID;
        url += isGetDistrict == true ? "?getdistrict=true" : "";
    }
    //Hàm lấy danh sách huyện
    public List<ProvinceBean> getListProvince() throws ExecutionException, InterruptedException {
        JsonObject output = new MyFoodyGetMethod(null, context, null).execute(url).get();

        List<ProvinceBean> districtList = null;
        if (output.get("success").toString().equals("true")) {
            Gson gson = new Gson();
            districtList = gson.fromJson(output.get("data"), new TypeToken<List<ProvinceBean>>() {
            }.getType());
        }
        // Log.d("OUTTTTTT0",districtList.get(0).getListStreet().get(0).getTitle());
        return districtList;
    }
}
