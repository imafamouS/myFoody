package com.fdsa.infamous.myfoody.common.bean_F2;

import java.util.List;

/**
 * Created by FDSA on 4/23/2017.
 */

public class DistrictBean {
    private String id;
    private String title;
    private List<StreetBean> listStreet;
    private int numofStreet;
    private boolean isSelected;

    public DistrictBean(String id, String title, List<StreetBean> listStreetBeans, int numofStreet, boolean isSelected){
        this.id=id;
        this.title=title;
        this.listStreet=listStreetBeans;
        this.numofStreet=numofStreet;
        this.isSelected=isSelected;
    }

    public DistrictBean(String id, String title, boolean isSelected) {
        this.id = id;
        this.title = title;
        this.isSelected=isSelected;
    }

    public DistrictBean(String id, String title, int numofStreet, boolean isSelected) {
        this.id = id;
        this.title = title;
        this.numofStreet = numofStreet;
        this.isSelected=isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public List<StreetBean> getListStreet() {
        return listStreet;
    }

    public void setListStreet(List<StreetBean> listStreet) {
        this.listStreet = listStreet;
    }

    public int getNumofStreet() {
        return numofStreet;
    }

    public void setNumofStreet(int numofStreet) {
        this.numofStreet = numofStreet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
