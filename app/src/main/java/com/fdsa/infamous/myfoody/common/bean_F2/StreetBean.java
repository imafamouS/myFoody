package com.fdsa.infamous.myfoody.common.bean_F2;

/**
 * Created by FDSA on 4/23/2017.
 */

public class StreetBean {
    private String id;
    private String title;
    private String districtid;
    private boolean isSelected;

    public StreetBean(String id, String title, String provinceID,boolean isSelected) {
        super();
        this.id = id;
        this.title = title;
        this.districtid = provinceID;
        this.isSelected=isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
