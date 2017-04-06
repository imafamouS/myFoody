package com.fdsa.infamous.myfoody.ui.util.bean;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */


public class District {
    String idDistrict;
    String titleDistrict;
    int numofStreet;
    List<Street> streetList;
    boolean isSelected;
    String idProvince;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public District(String idDistrict, String tittleDistrict, List<Street> streetList) {
        this.idDistrict = idDistrict;
        this.titleDistrict = tittleDistrict;
        this.streetList = streetList;
    }

    public District(String idDistrict, String titleDistrict,int numofStreet, boolean isSelected) {
        this.idDistrict = idDistrict;
        this.titleDistrict = titleDistrict;
        this.numofStreet=numofStreet;
        this.isSelected=isSelected;
    }

    public District(String idDistrict, String titleDistrict, int numofStreet, boolean isSelected, String idProvince) {
        this.idDistrict = idDistrict;
        this.titleDistrict = titleDistrict;
        this.numofStreet = numofStreet;
        this.isSelected = isSelected;
        this.idProvince = idProvince;
    }

    public String getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(String idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getTitleDistrict() {
        return titleDistrict;
    }

    public void setTitleDistrict(String titleDistrict) {
        this.titleDistrict = titleDistrict;
    }

    public int getNumofStreet() {
        return numofStreet;
    }

    public void setNumofStreet(int numofStreet) {
        this.numofStreet = numofStreet;
    }

    public List<Street> getStreetList() {
        return streetList;
    }

    public void setStreetList(List<Street> streetList) {
        this.streetList = streetList;
    }

    public String getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }
}


