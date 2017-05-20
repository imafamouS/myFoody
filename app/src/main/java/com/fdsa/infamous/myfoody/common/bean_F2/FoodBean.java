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
    private PositionBean position;

    //Hàm khởi tạo
    public FoodBean(String id, String title, String res_id, String name_res, String address_res, String photo) {
        this.id = id;
        this.title = title;
        this.res_id = res_id;
        this.name_res = name_res;
        this.address_res = address_res;
        this.photo = photo;
    }
    //Hàm lấy ID
    public String getId() {
        return id;
    }
    //Hàm gán ID
    public void setId(String id) {
        this.id = id;
    }
    //Hàm lấy tên food
    public String getTitle() {
        return title;
    }
    //hàm đặt tên food
    public void setTitle(String title) {
        this.title = title;
    }
    //Hàm lấy ID nhà hàng
    public String getRes_id() {
        return res_id;
    }
    //Hàm gán ID nhà hàng
    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }
    //Hàm lấy tên nhà hàng
    public String getName_res() {
        return name_res;
    }
    //Hàm gán tên nhà hàng
    public void setName_res(String name_res) {
        this.name_res = name_res;
    }
    //Hàm lấy địa chỉ nhà hàng
    public String getAddress_res() {
        return address_res;
    }
    //hàm gán địa chỉ nhà hàng
    public void setAddress_res(String address_res) {
        this.address_res = address_res;
    }
    //Hàm lấy ảnh
    public String getPhoto() {
        return photo;
    }
    //Hàm gán ảnh
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    //Hàm lấy tổng lượt xem
    public int getTotal_review() {
        return total_review;
    }
    //Hàm gán tổng lượt xem
    public void setTotal_review(int total_review) {
        this.total_review = total_review;
    }
    //Hàm lấy danh sách comment
    public List<CommentFoodBean> getListComment() {
        return listComment;
    }
    //hàm gán danh sách comment
    public void setListComment(List<CommentFoodBean> listComment) {
        this.listComment = listComment;
    }
    //Hàm lấy các ảnh phụ của nhà hàng
    public List<MoreImageRestaurantBean> getListImage() {
        return listImage;
    }
    //Hàm gán các ảnh phụ của nhà hàng
    public void setListImage(List<MoreImageRestaurantBean> listImage) {
        this.listImage = listImage;
    }
    //Hàm lấy vị trí của nhà hàng
    public PositionBean getPosition() {
        return position;
    }
    //Hàm gán vị trí của nhà hàng
    public void setPosition(PositionBean position) {
        this.position = position;
    }


}
