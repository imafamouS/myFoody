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
    //Hàm khởi tạo
    public ProvinceBean(String id, String title, List<DistrictBean> listDistrictBean, String countryID) {
        this.id = id;
        this.title = title;
        this.listDistrictBean = listDistrictBean;
        this.countryID = countryID;
    }
    //Hàm khởi tạo
    public ProvinceBean(String id, String title, String countryID) {
        this.id = id;
        this.title = title;
        this.countryID = countryID;
    }
    //Hàm lấy id
    public String getId() {
        return id;
    }
    //Hàm gán id
    public void setId(String id) {
        this.id = id;
    }
    //Hàm lấy tên
    public String gettitle() {
        return title;
    }
    //Hàm gán tên
    public void settitle(String title) {
        this.title = title;
    }
    //Hàm lấy danh sách huyện
    public List<DistrictBean> getListDistrictBean() {
        return listDistrictBean;
    }
    //Hàm gán danh sách huyện
    public void setListDistrictBean(List<DistrictBean> listDistrictBean) {
        this.listDistrictBean = listDistrictBean;
    }
    //hàm lấy id nước
    public String getCountryID() {
        return countryID;
    }
    //Hàm gán id nước
    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
}
