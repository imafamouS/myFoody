package com.fdsa.infamous.myfoody.config.api;

import com.google.gson.JsonObject;

/**
 * Created by FDSA on 4/22/2017.
 */

public class APIAction {

    /**
     * POST METHOD
     **/
    public static final int POST_LOGIN = 0;
    public static final int POST_IMAGE = 1;

    /**
     * GET METHOD
     **/

    public static final int GET_CATEGORY_WHAT2DO = 0;
    public static final int GET_CATEGORY_WHERE2DO = 1;
    public static final int GET_PROVINCE = 2;
    public static final int GET_DISTRICT = 3;
    public static final int GET_RESTAURANT = 4;
    public static final int GET_FOOD = 5;

    /**
     * ERROR_OBJECT
     */
    public static final JsonObject GET_ERROR_OBJECT() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("success", false);
        jsonObject.addProperty("message", "Something went wrong");

        return jsonObject;
    }
}
