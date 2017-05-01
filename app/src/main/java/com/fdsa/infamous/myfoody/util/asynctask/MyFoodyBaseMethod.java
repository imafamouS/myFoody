package com.fdsa.infamous.myfoody.util.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.fdsa.infamous.myfoody.util.JsonHTTPHelper;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by FDSA on 4/19/2017.
 */

public abstract class MyFoodyBaseMethod extends AsyncTask<String,String,JsonObject> {

    public MyFoodyBaseMethod(@Nullable boolean type,
                             @Nullable JsonObject jsonObjectInput,
                             @Nullable Context activity
                            ){
        super();
        this.type=type;
        this.jsonObjectInput=jsonObjectInput;
        this.activity=activity;
    }
    public JsonObject getOuput() throws ExecutionException, InterruptedException {
        return this.get();
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(JsonObject jsonObject) {
        super.onPostExecute(jsonObject);
        Log.d("POST_EXECUTE", jsonObject.toString());
    }

    @Override
    protected void onProgressUpdate(String... values) {

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

    private boolean type;
    private JsonObject jsonObjectInput;
    private Context activity;

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

}
