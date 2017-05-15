package com.fdsa.infamous.myfoody.util.controller_F2;

import android.content.Context;

import com.fdsa.infamous.myfoody.common.bean_F2.UserBean;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyGetMethod;
import com.fdsa.infamous.myfoody.util.asynctask.MyFoodyPostMethod;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by apple on 4/25/17.
 */

public class UserController {

    private Context context;
    private String url;
    public UserController(Context context){
        this.context=context;

    }

    public UserBean checkLogin(JsonObject loginJson) throws ExecutionException, InterruptedException {
        url="login";

        JsonObject output=new MyFoodyPostMethod(loginJson, context,null).execute(url).get();
        UserBean user=null;
        if(output.get("success").toString().equals("true")){
            Gson gson = new Gson();
            user=gson.fromJson(output.get("data"), UserBean.class);
        }
        return user;
    }
    public boolean register(JsonObject loginJson) throws ExecutionException, InterruptedException {
        url="user/register";

        JsonObject output=new MyFoodyPostMethod(loginJson, context,null).execute(url).get();
        if(output.get("success").toString().equals("true")){
           return true;
        }
        return false;
    }
    public boolean update(JsonObject updateJson) throws ExecutionException, InterruptedException {
        url="user/update";

        JsonObject output=new MyFoodyPostMethod(updateJson, context,null).execute(url).get();
        if(output.get("success").toString().equals("true")){
            return true;
        }
        return false;
    }
    public UserBean getuser(String userid,String token) throws ExecutionException, InterruptedException {
        url="user?userid="+userid;
        url+="&token="+token;

        JsonObject output=new MyFoodyGetMethod(null, context,null).execute(url).get();
        UserBean user=null;
        if(output.get("success").toString().equals("true")){
            Gson gson = new Gson();
            user=gson.fromJson(output.get("data"), UserBean.class);
        }
        return user;
    }
    public boolean changePass(JsonObject input) throws ExecutionException, InterruptedException {
        url="user/changepass";

        JsonObject output=new MyFoodyPostMethod(input, context,null).execute(url).get();
        if(output.get("success").toString().equals("true")){
            return true;
        }
        return false;
    }
    public synchronized boolean changeAvatar(JsonObject input,int action,String token)throws ExecutionException, InterruptedException {
        if(action==0){
            url="user/changeavatar";
        }else if(action==1){
            url="user/changecover";
        }
        url+="?token="+token;
        JsonObject output=new MyFoodyPostMethod(input, context,null).execute(url).get();
        if(output.get("success").toString().equals("true")){
            return true;
        }
        return false;
    }

}
