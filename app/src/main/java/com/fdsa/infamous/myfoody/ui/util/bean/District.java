package com.fdsa.infamous.myfoody.ui.util.bean;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */


public class District{
    String idDistrict;
    String titleDistrict;
    int numofStreet;
    List<Street> streetList;

    public District(String idDistrict, String tittleDistrict, List<Street> streetList) {
        this.idDistrict = idDistrict;
        this.titleDistrict = tittleDistrict;
        this.streetList = streetList;
    }

    public String getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(String idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getTittleDistrict() {
        return titleDistrict;
    }

    public void setTittleDistrict(String tittleDistrict) {
        this.titleDistrict = tittleDistrict;
    }

    public List<Street> getStreetList() {
        return streetList;
    }

    public void setStreetList(List<Street> streetList) {
        this.streetList = streetList;
    }

    public int getNumofStreet() {
        return numofStreet;
    }

    public void setNumofStreet(int numofStreet) {
        this.numofStreet = numofStreet;
    }
}
