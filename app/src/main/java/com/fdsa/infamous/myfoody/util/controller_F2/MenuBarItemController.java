package com.fdsa.infamous.myfoody.util.controller_F2;

import android.content.Context;
import android.util.Log;

import com.fdsa.infamous.myfoody.common.bean_F2.MenuBarItemBean;
import com.fdsa.infamous.myfoody.config.api.APIAction;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyGetMethod;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/22/2017.
 */

public class MenuBarItemController {
    private String url;
    private Context context;
    public MenuBarItemController(Context context, int action){
        this.context=context;

        if(action== APIAction.GET_CATEGORY_WHAT2DO){
            url="api/menubar/get/category_what2do";
        }else if(action==APIAction.GET_CATEGORY_WHERE2DO){
            url="api/menubar/get/category_where2go";
        }else{
            url="";
        }
    }
    public List<MenuBarItemBean> getListMenuBar_Category() throws ExecutionException, InterruptedException {
        JsonObject output=new MyFoodyGetMethod(null, context).execute(url).get();
        Log.d("OUTTTTTT0",output.toString());
        List<MenuBarItemBean> menuBarItemList=null;
        if(output.get("success").toString().equals("true")){
            Gson gson = new Gson();
            menuBarItemList=gson.fromJson(output.get("data"), new TypeToken<List<MenuBarItemBean>>(){}.getType());
        }
       return menuBarItemList;
    }
}
