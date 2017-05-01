package com.fdsa.infamous.myfoody.common.bean_F2;

public class CommentFoodBean {
	private String id;
	private String userid;
	private String foodid;
	private String comment;
	private UserBean user;
	
	public CommentFoodBean(String id, UserBean user, String foodid, String comment) {
		super();
		this.id = id;
		this.user = user;
		this.foodid = foodid;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public String getFoodid() {
		return foodid;
	}

	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}
	
}
