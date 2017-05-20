package com.fdsa.infamous.myfoody.common.bean_F2;

public class CommentFoodBean {
    private String id;
    private String userid;
    private String foodid;
    private String comment;
    private UserBean user;

    //Hàm khởi tạo
    public CommentFoodBean(String id, UserBean user, String foodid, String comment) {
        super();
        this.id = id;
        this.user = user;
        this.foodid = foodid;
        this.comment = comment;
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
    //Hàm lấy các comment
    public String getComment() {
        return comment;
    }
    //Hàm gán các comment
    public void setComment(String comment) {
        this.comment = comment;
    }
    //Hàm lấy User của comment
    public UserBean getUser() {
        return user;
    }
    //Hàm gán User
    public void setUser(UserBean user) {
        this.user = user;
    }
    //Hàm lấy id của food
    public String getFoodid() {
        return foodid;
    }
    //Hàm gán id của food
    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }

}
