package com.fdsa.infamous.myfoody.ui.util.bean;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */


public class District {
    private String idDistrict;
    private String titleDistrict;
    private int numofStreet;
    private List<Street> streetList;
    private boolean isSelected;
    private String idProvince;

    //Hàm khởi tạo quận, huyện
    public District(String idDistrict, String tittleDistrict, List<Street> streetList) {
        this.idDistrict = idDistrict;
        this.titleDistrict = tittleDistrict;
        this.streetList = streetList;
    }

    //Hàm khởi tạo quận, huyện
    public District(String idDistrict, String titleDistrict, int numofStreet, boolean isSelected) {
        this.idDistrict = idDistrict;
        this.titleDistrict = titleDistrict;
        this.numofStreet = numofStreet;
        this.isSelected = isSelected;
    }

    //Hàm khởi tạo quận, huyện
    public District(String idDistrict, String titleDistrict, int numofStreet, boolean isSelected, String idProvince) {
        this.idDistrict = idDistrict;
        this.titleDistrict = titleDistrict;
        this.numofStreet = numofStreet;
        this.isSelected = isSelected;
        this.idProvince = idProvince;
    }

    //Hàm get id quận, huyện
    public String getIdDistrict() {
        return idDistrict;
    }

    //Hàm set id quận, huyện
    public void setIdDistrict(String idDistrict) {
        this.idDistrict = idDistrict;
    }

    //Hàm get tên quận, huyện
    public String getTitleDistrict() {
        return titleDistrict;
    }

    //Hàm set tên quận, huyện
    public void setTitleDistrict(String titleDistrict) {
        this.titleDistrict = titleDistrict;
    }

    //Hàm get số đường của quận, huyện
    public int getNumofStreet() {
        return numofStreet;
    }

    //Hàm set số đường của quận, huyện
    public void setNumofStreet(int numofStreet) {
        this.numofStreet = numofStreet;
    }

    //Hàm get danh sách đường của quận huyện
    public List<Street> getStreetList() {
        return streetList;
    }

    //Hàm set danh sách đường của quận huyện
    public void setStreetList(List<Street> streetList) {
        this.streetList = streetList;
    }

    //Hàm get id của tỉnh
    public String getIdProvince() {
        return idProvince;
    }

    //Hàm set id của tỉnh
    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }

    //Hàm kiểm tra quận, huyện có được chọn
    public boolean isSelected() {
        return isSelected;
    }

    //Hàm settrang thái  được chọn của quận, huyện
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}


