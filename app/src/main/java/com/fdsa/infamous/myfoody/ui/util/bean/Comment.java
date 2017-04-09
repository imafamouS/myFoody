package com.fdsa.infamous.myfoody.ui.util.bean;

/**
 * Created by FDSA on 4/8/2017.
 */

public class Comment {

    private String id;
    private User uid;
    private String resid;
    private double rate;
    private String text;

    //Hàm khởi tạo bình luận
    public Comment(String id, User uid, String resid, double rate, String text) {
        this.id = id;
        this.uid = uid;
        this.resid = resid;
        this.rate = rate;
        this.text = text;
    }

    //Hàm get id bình luận
    public String getId() {
        return id;
    }

    //Hàm set id bình luận
    public void setId(String id) {
        this.id = id;
    }

    //Hàm get user bình luận
    public User getUid() {
        return uid;
    }

    //Hàm set user bình luận
    public void setUid(User uid) {
        this.uid = uid;
    }

    //Hàm get id nhà hàng
    public String getResid() {
        return resid;
    }

    //Hàm set id nhà hàng
    public void setResid(String resid) {
        this.resid = resid;
    }

    //Hàm get số điểm bình chọn
    public double getRate() {
        return rate;
    }

    //Hàm set số điểm bình chọn
    public void setRate(double rate) {
        this.rate = rate;
    }

    //Hàm get nội dung bình luận
    public String getText() {
        return text;
    }

    //Hàm set nội dung bình luận
    public void setText(String text) {
        this.text = text;
    }
}
