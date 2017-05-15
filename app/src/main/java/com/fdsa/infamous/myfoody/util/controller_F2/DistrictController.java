package com.fdsa.infamous.myfoody.util.controller_F2;

import android.content.Context;

import com.fdsa.infamous.myfoody.common.bean_F2.DistrictBean;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyGetMethod;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/23/2017.
 */

public class DistrictController {
    private Context context;
    private String url;
    public DistrictController(Context context){
    }
    public List<DistrictBean> getListDistrict(String provinceID) throws ExecutionException, InterruptedException {
        url="api/district/get/"+provinceID;
        JsonObject output=new MyFoodyGetMethod(null, context,null).execute(url).get();

        List<DistrictBean> districtList=null;
        if(output.get("success").toString().equals("true")){
            Gson gson = new Gson();
            districtList=gson.fromJson(output.get("data"), new TypeToken<List<DistrictBean>>(){}.getType());
        }

        return districtList;
    }
}
