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

    public CommentResBean(String id, UserBean user, String resid, String comment, double rating) {
        super();
        this.id = id;
        this.user = user;
        this.resid = resid;
        this.comment = comment;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getResid() {
        return resid;
    }
    public void setResid(String resid) {
        this.resid = resid;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }



}
