package com.fdsa.infamous.myfoody.util.asynctask;

import android.content.Context;
import android.support.annotation.Nullable;

import com.fdsa.infamous.myfoody.common.myinterface.ICallBackAsynsTask;
import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/19/2017.
 */

public class MyFoodyGetMethod extends MyFoodyBaseMethod{

    public MyFoodyGetMethod(@Nullable JsonObject jsonObjectInput, @Nullable Context activity, ICallBackAsynsTask callBackAsynsTask) {
        super(APIConfig.GET, jsonObjectInput, activity,callBackAsynsTask);
    }

    @Override
    public JsonObject getOuput() throws ExecutionException, InterruptedException {
        return super.getOuput();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(JsonObject jsonObject) {
        super.onPostExecute(jsonObject);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(JsonObject jsonObject) {
        super.onCancelled(jsonObject);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected JsonObject doInBackground(String... params) {
        return super.doInBackground(params);
    }

    @Override
    public boolean isType() {
        return super.isType();
    }

    @Override
    public void setType(boolean type) {
        super.setType(type);
    }

    @Override
    public JsonObject getJsonObjectInput() {
        return super.getJsonObjectInput();
    }

    @Override
    public void setJsonObjectInput(JsonObject jsonObjectInput) {
        super.setJsonObjectInput(jsonObjectInput);
    }

    @Override
    public Context getActivity() {
        return super.getActivity();
    }

    @Override
    public void setActivity(Context activity) {
        super.setActivity(activity);
    }


}
