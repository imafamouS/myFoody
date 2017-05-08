package com.fdsa.infamous.myfoody.util.controller_F2;

import android.content.Context;
import android.util.Log;

import com.fdsa.infamous.myfoody.common.bean_F2.RestaurantBean;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyGetMethod;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/23/2017.
 */

public class RestaurantController {
    private Context context;
    private String url;
    public RestaurantController(Context context){
        this.context=context;

    }

    public List<RestaurantBean> getListRestaurant(String provinceid,String districtid,String streetid,String wheretype,String sorttype) throws ExecutionException, InterruptedException {
       // url="api/restaurant/get?provinceid="+provinceid+"&districtid="+districtid+"&streetid="+streetid+"&wheretype="+wheretype+"&sorttype="+sorttype;
        url="api/restaurant/get?provinceid="+provinceid;
        if(districtid!=null &&!districtid.equals("")){
            url+="&districtid="+districtid;
        }
        if(streetid!=null&&!streetid.equals("")){
            url+="&streetid="+streetid;
        }
        if(wheretype!=null&&!wheretype.equals("")){
            url+="&wheretype="+wheretype;
        }
        if(sorttype!=null&&!sorttype.equals("")) {
            url += "&sort=" + sorttype;
        }
        Log.d("URL",url);
        JsonObject output=new MyFoodyGetMethod(null, context).execute(url).get();
        List<RestaurantBean> restaurantList=null;
        if(output.get("success").toString().equals("true")){
            Gson gson = new Gson();
            restaurantList=gson.fromJson(output.get("data"), new TypeToken<List<RestaurantBean>>(){}.getType());
        }
        return restaurantList;

    }
    public RestaurantBean getRestaurantById(String id) throws ExecutionException, InterruptedException {
        url="api/restaurant/getbyid/"+id;
        JsonObject output=new MyFoodyGetMethod(null, context).execute(url).get();
        RestaurantBean restaurant=new RestaurantBean();
        if(output.get("success").toString().equals("true")){
            Gson gson = new Gson();
            restaurant=gson.fromJson(output.get("data"), RestaurantBean.class);
        }

        Log.d("TAGGGG_RES",output.toString());
        return restaurant;

    }
}
