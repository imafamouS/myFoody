package com.fdsa.infamous.myfoody.common.bean_F2;

/**
 * Created by FDSA on 4/23/2017.
 */

public class CommentResBean {
    private String id;
    private String userid;
    private String resid;
    private String comment;
    private double rating;
    private UserBean user;
    //Hàm khởi tạo
    public CommentResBean(String id, UserBean user, String resid, String comment, double rating) {
        super();
        this.id = id;
        this.user = user;
        this.resid = resid;
        this.comment = comment;
        this.rating = rating;
    }
    //Hàm lấy ID
    public String getId() {
        return id;
    }
    //Hàm gán ID
    public void setId(String id) {
        this.id = id;
    }
    //Hàm lấy UserID
    public String getUserid() {
        return userid;
    }
    //Hàm gán UserID
    public void setUserid(String userid) {
        this.userid = userid;
    }
    //Hàm lấy Restaurant ID
    public String getResid() {
        return resid;
    }
    //Hàm gán RestaurantID
    public void setResid(String resid) {
        this.resid = resid;
    }
    //Hàm lấy comment
    public String getComment() {
        return comment;
    }
    //Hàm gán comment
    public void setComment(String comment) {
        this.comment = comment;
    }
    //Hàm lấy điểm số bình chọn
    public double getRating() {
        return rating;
    }
    //Hàm gán điểm số bình chọn
    public void setRating(double rating) {
        this.rating = rating;
    }
    //Hàm lấy User (chủ nhân comment)
    public UserBean getUser() {
        return user;
    }
    //Hàm gán User
    public void setUser(UserBean user) {
        this.user = user;
    }


}
