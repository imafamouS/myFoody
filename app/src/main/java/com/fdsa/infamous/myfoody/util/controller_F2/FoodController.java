package com.fdsa.infamous.myfoody.util.controller_F2;

import android.content.Context;
import android.util.Log;

import com.fdsa.infamous.myfoody.common.bean_F2.FoodBean;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyGetMethod;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/23/2017.
 */

public class FoodController {
    private Context context;
    private String url;
    public FoodController(Context context){
        this.context=context;
    }
    public List<FoodBean> getListFood(String provinceid,String districtid,String streetid,String restype,String sorttype) throws ExecutionException, InterruptedException {

        url="api/food/get?provinceid="+provinceid;
        if(districtid!=null &&!districtid.equals("")){
            url+="&districtid="+districtid;
        }
        if(streetid!=null&&!streetid.equals("")){
            url+="&streetid="+streetid;
        }
        if(restype!=null&&!restype.equals("")){
            url+="&restype="+restype;
        }
        if(sorttype!=null&&!sorttype.equals("")){
            url+="&sort="+sorttype;
        }
        Log.d("FOOD",url);

        JsonObject output=new MyFoodyGetMethod(null, context).execute(url).get();

        List<FoodBean> foodList=null;
        if(output.get("success").toString().equals("true")){
            Gson gson = new Gson();
            foodList=gson.fromJson(output.get("data"), new TypeToken<List<FoodBean>>(){}.getType());
        }
        return foodList;
    }
}
