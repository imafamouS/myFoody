package com.fdsa.infamous.myfoody.ui.util.bean;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */

public class Province {
    String idProvince;
    String titleProvince;
    List<District> districtList;


    public Province(String idProvince, String titleProvince, List<District> districtList) {
        this.idProvince = idProvince;
        this.titleProvince = titleProvince;
        this.districtList = districtList;
    }

    public String getIdProvince() {
        return idProvince;
    }


    public String getTitleProvince() {
        return titleProvince;
    }

    public List<District> getDistrictList() {
        return districtList;
    }




}
