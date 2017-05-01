package com.fdsa.infamous.myfoody.common.bean_F2;

/**
 * Created by FDSA on 4/23/2017.
 */

public class MoreImageRestaurantBean {
    private String id;
    private String resid;
    private String photo;

    public MoreImageRestaurantBean(String id, String resid, String photo) {
        super();
        this.id = id;
        this.resid = resid;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getResid() {
        return resid;
    }
    public void setResid(String resid) {
        this.resid = resid;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
