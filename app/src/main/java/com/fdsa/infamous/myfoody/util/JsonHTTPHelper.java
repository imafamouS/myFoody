package com.fdsa.infamous.myfoody.util;

import android.support.annotation.Nullable;

import com.fdsa.infamous.myfoody.config.api.APIAction;
import com.fdsa.infamous.myfoody.config.api.APIConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by FDSA on 4/22/2017.
 */

public class JsonHTTPHelper {
    //Hàm thực hiện việc gửi và nhận dữ liệu lên URL
    public synchronized  static JsonObject makeHttpResponse(String url, boolean method, @Nullable JsonObject jsonObjectInput) {
        URLConnection connection = getHTTPConnection(url, method);
        if (connection == null)
            return APIAction.GET_ERROR_OBJECT();
        if (jsonObjectInput != null) {
            postObject(jsonObjectInput, connection);
        }

        return getJsonResponse(connection);

    }
    //Hàm đưa dữ liệu lên server
    private static void postObject(JsonObject jsonObjectInput, URLConnection connection) {

        try {
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObjectInput.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Hàm lấy kết quả trả về từ URL
    private static JsonObject getJsonResponse(URLConnection connection) {

        JSONObject jsonObject = getJSONObjectFromConnection(connection);
        return convertJSONObject2JSonObject(jsonObject);

    }
    //Hàm chuyển đổi từ JSONObject sang JsonObject(Gson_Google)
    private static JsonObject convertJSONObject2JSonObject(JSONObject input) {
        if (input != null) {
            return (JsonObject) new JsonParser().parse(input.toString());
        }
        return APIAction.GET_ERROR_OBJECT();

    }
    //Hàm tạo kết nối đến URL
    private static URLConnection getHTTPConnection(String strURL, boolean method) {
        URL url = null;
        URLConnection connection = null;
        try {
            url = new URL(strURL);
            connection = url.openConnection();
            if (method == APIConfig.POST) {
                connection.setDoOutput(true);
            } else if (method == APIConfig.GET) {
                connection.setDoOutput(false);
            }
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
    //Hàm lấy JSONObject từ URL
    private static JSONObject getJSONObjectFromConnection(URLConnection connection) {
        JSONObject output = null;
        if (connection != null) {
            InputStream in = null;
            try {
                in = new BufferedInputStream(connection.getInputStream());
                String result = IOUtils.toString(in, "UTF-8");
                output = new JSONObject(result);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }

        return output;
    }
}
