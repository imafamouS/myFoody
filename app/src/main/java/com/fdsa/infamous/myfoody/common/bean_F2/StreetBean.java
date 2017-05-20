package com.fdsa.infamous.myfoody.common.bean_F2;

/**
 * Created by FDSA on 4/23/2017.
 */

public class StreetBean {
    private String id;
    private String title;
    private String districtid;
    private boolean isSelected;

    //Hàm khởi tạo
    public StreetBean(String id, String title, String provinceID, boolean isSelected) {
        super();
        this.id = id;
        this.title = title;
        this.districtid = provinceID;
        this.isSelected = isSelected;
    }
    //Hàm lấy id
    public String getId() {
        return id;
    }
    //hàm gán id
    public void setId(String id) {
        this.id = id;
    }
    //Hàm lấy tên đường
    public String getTitle() {
        return title;
    }
    //Hàm gán tên đường
    public void setTitle(String title) {
        this.title = title;
    }
    //hàm lấy id huyện
    public String getDistrictid() {
        return districtid;
    }
    //Hàm gán id huyện
    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }
    //Hàm kiểm tra đường có được chọn
    public boolean isSelected() {
        return isSelected;
    }
    //Hàm gán sự lựa chọn của đường
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
