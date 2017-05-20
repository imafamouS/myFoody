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

    //Hàm khởi tạo
    public DistrictBean(String id, String title, List<StreetBean> listStreetBeans, int numofStreet, boolean isSelected) {
        this.id = id;
        this.title = title;
        this.listStreet = listStreetBeans;
        this.numofStreet = numofStreet;
        this.isSelected = isSelected;
    }
    //Hàm khởi tạo
    public DistrictBean(String id, String title, boolean isSelected) {
        this.id = id;
        this.title = title;
        this.isSelected = isSelected;
    }
    //Hàm khởi tạo
    public DistrictBean(String id, String title, int numofStreet, boolean isSelected) {
        this.id = id;
        this.title = title;
        this.numofStreet = numofStreet;
        this.isSelected = isSelected;
    }
    //Hàm lấy ID
    public String getId() {
        return id;
    }
    //Hàm gán ID
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
    //hàm lấy danh sách đường trong quận
    public List<StreetBean> getListStreet() {
        return listStreet;
    }
    //Hàm gán danh sách đường trong quận
    public void setListStreet(List<StreetBean> listStreet) {
        this.listStreet = listStreet;
    }
    //Hàm lấy số lượng đường
    public int getNumofStreet() {
        return numofStreet;
    }
    //Hàm gán số lượng đường
    public void setNumofStreet(int numofStreet) {
        this.numofStreet = numofStreet;
    }
    //Hàm lấy tên
    public String getTitle() {
        return title;
    }
    //Hàm gán tên
    public void setTitle(String title) {
        this.title = title;
    }
    //Hàm kiểm tra xem huyện có được chọn
    public boolean isSelected() {
        return isSelected;
    }
    //Hàm gán huyện được chọn hoặc không
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
