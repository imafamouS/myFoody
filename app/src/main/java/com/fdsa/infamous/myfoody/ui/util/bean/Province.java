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

    public Province(String idProvince, String titleProvince) {
        this.idProvince = idProvince;
        this.titleProvince = titleProvince;
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

    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }
}
