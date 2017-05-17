package com.fdsa.infamous.myfoody.util.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.fdsa.infamous.myfoody.common.myinterface.ICallBackAsynsTask;
import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.fdsa.infamous.myfoody.util.JsonHTTPHelper;
import com.google.gson.JsonObject;

/**
 * Created by apple on 5/15/17.
 */

public class MyDecodeLocationMethod extends AsyncTask<Object, String, String> {

    Context context;
    double lat;
    double lng;
    ICallBackAsynsTask callBackAsynsTask;

    public MyDecodeLocationMethod(Context context, double lat, double lng, ICallBackAsynsTask callBackAsynsTask) {
        this.context = context;
        this.lat = lat;
        this.lng = lng;
        this.callBackAsynsTask = callBackAsynsTask;
    }


    @Override
    protected void onPostExecute(String strings) {
        super.onPostExecute(strings);
        if (strings.equals("") || strings.length() <= 0) {
            callBackAsynsTask.onFail(strings);
        } else {
            callBackAsynsTask.onSuccess(strings);
        }

    }

    @Override
    protected void onCancelled() {

    }

    @Override
    protected String doInBackground(Object... params) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + this.lat + "," + this.lng + "&sensor=false";
        JsonObject responseJsonFromURL = JsonHTTPHelper.makeHttpResponse(url, APIConfig.GET, null);


        if (responseJsonFromURL == null) {
            return "";
        } else {
            try {
                return responseJsonFromURL.get("results").getAsJsonArray().get(0).getAsJsonObject().get("formatted_address").toString() != null ?
                        responseJsonFromURL.get("results").getAsJsonArray().get(0).getAsJsonObject().get("formatted_address").toString().replaceAll("\"", "") : "";
            } catch (Exception e) {
                return "";
            }
        }
    }
}
