package com.fdsa.infamous.myfoody.common.bean_F2;

import java.util.List;

/**
 * Created by FDSA on 4/23/2017.
 */

public class FoodBean {
    private String id;
    private String title;
    private String res_id;
    private String name_res;
    private String address_res;
    private String photo;
    private int total_review;
    private List<CommentFoodBean> listComment;
    private List<MoreImageRestaurantBean> listImage;

    public FoodBean(String id, String title, String res_id, String name_res, String address_res, String photo) {
        this.id = id;
        this.title = title;
        this.res_id = res_id;
        this.name_res = name_res;
        this.address_res = address_res;
        this.photo = photo;
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

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getName_res() {
        return name_res;
    }

    public void setName_res(String name_res) {
        this.name_res = name_res;
    }

    public String getAddress_res() {
        return address_res;
    }

    public void setAddress_res(String address_res) {
        this.address_res = address_res;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTotal_review() {
        return total_review;
    }

    public void setTotal_review(int total_review) {
        this.total_review = total_review;
    }

    public List<CommentFoodBean> getListComment() {
        return listComment;
    }

    public void setListComment(List<CommentFoodBean> listComment) {
        this.listComment = listComment;
    }

    public List<MoreImageRestaurantBean> getListImage() {
        return listImage;
    }

    public void setListImage(List<MoreImageRestaurantBean> listImage) {
        this.listImage = listImage;
    }



}
