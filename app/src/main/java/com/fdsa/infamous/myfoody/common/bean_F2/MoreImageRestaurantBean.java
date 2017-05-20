package com.fdsa.infamous.myfoody.common.bean_F2;

/**
 * Created by FDSA on 4/23/2017.
 */

public class MoreImageRestaurantBean {
    private String id;
    private String resid;
    private String photo;

    //Hàm khởi tạo
    public MoreImageRestaurantBean(String id, String resid, String photo) {
        super();
        this.id = id;
        this.resid = resid;
        this.photo = photo;
    }
    //Hàm lấy id
    public String getId() {
        return id;
    }
    //Hàm gán id
    public void setId(String id) {
        this.id = id;
    }
    //Hàm lấy id nhà hàng
    public String getResid() {
        return resid;
    }
    //Hàm gán id nhà hàng
    public void setResid(String resid) {
        this.resid = resid;
    }
    //Hàm lấy ảnh
    public String getPhoto() {
        return photo;
    }
    //Hàm gán ảnh
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
