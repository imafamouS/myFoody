package com.fdsa.infamous.myfoody.common.bean_F2;

import java.util.List;

/**
 * Created by FDSA on 4/3/2017.
 */

public class ProvinceBean {
    private String id;
    private String title;
    private List<DistrictBean> listDistrictBean;

    private String countryID;

    public ProvinceBean(String id, String title, List<DistrictBean> listDistrictBean, String countryID) {
        this.id = id;
        this.title = title;
        this.listDistrictBean = listDistrictBean;
        this.countryID=countryID;
    }
    public ProvinceBean(String id, String title, String countryID) {
        this.id = id;
        this.title = title;
        this.countryID=countryID;
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
    public List<DistrictBean> getListDistrictBean() {
        return listDistrictBean;
    }
    public void setListDistrictBean(List<DistrictBean> listDistrictBean) {
        this.listDistrictBean = listDistrictBean;
    }
    public String getCountryID() {
        return countryID;
    }
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
}
