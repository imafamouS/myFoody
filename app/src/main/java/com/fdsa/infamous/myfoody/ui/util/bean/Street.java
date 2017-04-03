package com.fdsa.infamous.myfoody.ui.util.bean;

/**
 * Created by FDSA on 4/3/2017.
 */

public class Street{
    String idStreet;
    String titleStreet;

    public Street(String idStreet, String tittleStreet) {
        this.idStreet = idStreet;
        this.titleStreet = tittleStreet;
    }

    public String getIdStreet() {
        return idStreet;
    }

    public void setIdStreet(String idStreet) {
        this.idStreet = idStreet;
    }

    public String getTittleStreet() {
        return titleStreet;
    }

    public void setTittleStreet(String tittleStreet) {
        this.titleStreet = tittleStreet;
    }
}