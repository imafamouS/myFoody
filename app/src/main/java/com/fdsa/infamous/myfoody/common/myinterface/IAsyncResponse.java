package com.fdsa.infamous.myfoody.common.myinterface;

import com.google.gson.JsonObject;

/**
 * Created by FDSA on 4/22/2017.
 */

public interface IAsyncResponse {
    void processFinish(int resultCode, JsonObject output);
}
