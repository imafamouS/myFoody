package com.fdsa.infamous.myfoody.GlobalData;

import java.util.Map;

/**
 * Created by FDSA on 3/26/2017.
 */

public class GlobalData {

    Map<String, String> currentCity;

    public GlobalData() {

    }

    public Map<String, String> getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(Map<String, String> currentCity) {
        this.currentCity = currentCity;
    }


}
