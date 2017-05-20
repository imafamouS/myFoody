package com.fdsa.infamous.myfoody.util.controller_F2;

import android.content.Context;
import android.util.Log;

import com.fdsa.infamous.myfoody.common.bean_F2.RestaurantBean;
import com.fdsa.infamous.myfoody.common.myinterface.ICallBackAsynsTask;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyGetMethod;
import com.fdsa.infamous.myfoody.util.global.GlobalStaticData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/23/2017.
 */

public class RestaurantController {
    ICallBackAsynsTask callback;
    private Context context;
    private String url;
    //Hàm khởi tạo
    public RestaurantController(Context context) {
        this.context = context;

    }
    //Hàm gán Interface CallBackAsysnTask
    public void setCallback(ICallBackAsynsTask callback) {
        this.callback = callback;
    }
    //Hàm lấy danh sach1 nhà hàng
    public List<RestaurantBean> getListRestaurant(String provinceid, String districtid, String streetid, String wheretype, String sorttype, String page) throws ExecutionException, InterruptedException {
        // url="api/restaurant/get?provinceid="+provinceid+"&districtid="+districtid+"&streetid="+streetid+"&wheretype="+wheretype+"&sorttype="+sorttype;
        url = "api/restaurant/get?provinceid=" + provinceid;
        if (districtid != null && !districtid.equals("")) {
            url += "&districtid=" + districtid;
        }
        if (streetid != null && !streetid.equals("")) {
            url += "&streetid=" + streetid;
        }
        if (wheretype != null && !wheretype.equals("")) {
            url += "&wheretype=" + wheretype;
        }
        if (sorttype != null && !sorttype.equals("")) {
            url += "&sort=" + sorttype;
            if (sorttype.equals("ganday")) {
                url += "&lat=" + GlobalStaticData.getMYLOCATION().getLatitude() + "&long=" + GlobalStaticData.getMYLOCATION().getLongitude();
            }
        }
        if (page != null && !page.equals("")) {
            url += "&page=" + page;
        }

        Log.d("URL", url);
        JsonObject output = new MyFoodyGetMethod(null, context, null).execute(url).get();
        List<RestaurantBean> restaurantList = new ArrayList<>();
        if (output.get("success").toString().equals("true")) {
            Gson gson = new Gson();
            restaurantList = gson.fromJson(output.get("data"), new TypeToken<List<RestaurantBean>>() {
            }.getType());
        }
        return restaurantList;

    }
    //Hàm lấy danh sách nhà hàng theo ID
    public RestaurantBean getRestaurantById(String id) throws ExecutionException, InterruptedException {
        url = "api/restaurant/getbyid/" + id;
        JsonObject output = new MyFoodyGetMethod(null, context, null).execute(url).get();
        RestaurantBean restaurant = new RestaurantBean();
        if (output.get("success").toString().equals("true")) {
            Gson gson = new Gson();
            restaurant = gson.fromJson(output.get("data"), RestaurantBean.class);
        }

        Log.d("TAGGGG_RES", output.toString());
        return restaurant;

    }
    //Hàm lấy số lượng nhà hàng
    public String getCountResturant(String provinceid, String districtid, String streetid, String wheretype, String sorttype) throws ExecutionException, InterruptedException {
        url = "api/restaurant/count?provinceid=" + provinceid;
        if (districtid != null && !districtid.equals("")) {
            url += "&districtid=" + districtid;
        }
        if (streetid != null && !streetid.equals("")) {
            url += "&streetid=" + streetid;
        }
        if (wheretype != null && !wheretype.equals("")) {
            url += "&wheretype=" + wheretype;
        }
        if (sorttype != null && !sorttype.equals("")) {
            url += "&sort=" + sorttype;
            if (sorttype.equals("ganday")) {
                url += "&lat=" + GlobalStaticData.getMYLOCATION().getLatitude() + "&long=" + GlobalStaticData.getMYLOCATION().getLongitude();
            }
        }
        JsonObject output = new MyFoodyGetMethod(null, context, null).execute(url).get();
        if(output!=null){
            return output.get("data")!=null?output.get("data").toString():"";
        }else{
            return "";
        }

    }
}
