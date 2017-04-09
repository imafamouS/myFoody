package com.fdsa.infamous.myfoody.ui.util.bean;

/**
 * Created by FDSA on 4/3/2017.
 */

public class Street{
    private String idStreet;
    private String titleStreet;

    //Hàm khởi tạo đối tượng đường
    public Street(String idStreet, String tittleStreet) {
        this.idStreet = idStreet;
        this.titleStreet = tittleStreet;
    }

    //Hàm get id đường
    public String getIdStreet() {
        return idStreet;
    }

    //Hàm set id đường
    public void setIdStreet(String idStreet) {
        this.idStreet = idStreet;
    }

    //Hàm get tên đường
    public String getTittleStreet() {
        return titleStreet;
    }

    //Hàm set tên đường
    public void setTittleStreet(String tittleStreet) {
        this.titleStreet = tittleStreet;
    }
}