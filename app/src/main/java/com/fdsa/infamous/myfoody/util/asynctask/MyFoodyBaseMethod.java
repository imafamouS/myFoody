package com.fdsa.infamous.myfoody.util.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.fdsa.infamous.myfoody.common.myinterface.ICallBackAsynsTask;
import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.fdsa.infamous.myfoody.util.JsonHTTPHelper;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/19/2017.
 */

public abstract class MyFoodyBaseMethod extends AsyncTask<String,String,JsonObject> {

    private boolean type;
    private JsonObject jsonObjectInput;
    private Context activity;
    private ICallBackAsynsTask callBackAsynsTask;

    public MyFoodyBaseMethod(@Nullable boolean type,
                             @Nullable JsonObject jsonObjectInput,
                             @Nullable Context activity,
                             @Nullable ICallBackAsynsTask callback
                            ){
        super();
        this.type=type;
        this.jsonObjectInput=jsonObjectInput;
        this.activity=activity;
        this.callBackAsynsTask=callback;
    }
    public JsonObject getOuput() throws ExecutionException, InterruptedException {
        return this.get();
    }
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(JsonObject jsonObject) {
        if(callBackAsynsTask!=null){
            if(jsonObject.get("success").toString().equals("true")){
                callBackAsynsTask.onSuccess(jsonObject);
            }else{
                callBackAsynsTask.onFail(jsonObject);
            }
        }
        super.onPostExecute(jsonObject);

    }

    @Override
    protected void onProgressUpdate(String... values) {
        callBackAsynsTask.onRunnin();
    }

    @Override
    protected void onCancelled(JsonObject jsonObject) {
    }

    @Override
    protected void onCancelled() {
    }

    @Override
    protected JsonObject doInBackground(String... params) {
        String absolutePath = APIConfig.BASE_URL + params[0];

        return JsonHTTPHelper.makeHttpResponse(absolutePath, type, jsonObjectInput);
    }



    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public JsonObject getJsonObjectInput() {
        return jsonObjectInput;
    }

    public void setJsonObjectInput(JsonObject jsonObjectInput) {
        this.jsonObjectInput = jsonObjectInput;
    }

    public Context getActivity() {
        return activity;
    }

    public void setActivity(Context activity) {
        this.activity = activity;
    }

    public ICallBackAsynsTask getCallBackAsynsTask() {
        return callBackAsynsTask;
    }

    public void setCallBackAsynsTask(ICallBackAsynsTask callBackAsynsTask) {
        this.callBackAsynsTask = callBackAsynsTask;
    }
}
