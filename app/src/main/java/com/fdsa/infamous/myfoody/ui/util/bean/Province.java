package com.fdsa.infamous.myfoody.ui.util.bean;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */

public class Province {
    List<District> districtList;
    private String idProvince;
    private String titleProvince;

    //Hàm khởi tạo Tỉnh
    public Province(String idProvince, String titleProvince, List<District> districtList) {
        this.idProvince = idProvince;
        this.titleProvince = titleProvince;
        this.districtList = districtList;
    }

    //Hàm khởi tạo Tỉnh
    public Province(String idProvince, String titleProvince) {
        this.idProvince = idProvince;
        this.titleProvince = titleProvince;
    }

    //Hàm get Id tỉnh
    public String getIdProvince() {
        return idProvince;
    }

    //Hàm set Id tỉnh
    public String getTitleProvince() {
        return titleProvince;
    }

    //Hàm get danh sách các quận huyện
    public List<District> getDistrictList() {
        return districtList;
    }

    //Hàm set danh sách các quận huyện
    public void setDistrictList(List<District> districtList) {
        this.districtList = districtList;
    }
}
