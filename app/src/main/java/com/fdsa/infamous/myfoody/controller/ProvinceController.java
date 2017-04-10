package com.fdsa.infamous.myfoody.controller;

import android.content.Context;
import android.database.Cursor;

import com.fdsa.infamous.myfoody.AppConfig;
import com.fdsa.infamous.myfoody.database.DataAccess;
import com.fdsa.infamous.myfoody.ui.util.bean.District;
import com.fdsa.infamous.myfoody.ui.util.bean.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/6/2017.
 */

public class ProvinceController extends DataAccess {

    Context context;

    //Hàm khởi tạo
    public ProvinceController(Context context) {
        super(context);
        this.context=context;
    }

    //Hàm mở kết nối
    @Override
    public void open() {
        super.open();
    }

    //Hàm đóng kết nối
    @Override
    public void close() {
        super.close();
    }

    //Hàm lấy danh sách các tỉnh hoặc danh sách các huyện
    @Override
    public List<?> executeSelect(String... params) {
        List<?> list=new ArrayList<>();
        String query = "";
        String db = "";
        if (params[0].equals(AppConfig.REQUEST_CODE_LIST_AREA)) {
            this.open();
            List<District> listDistricts=new ArrayList<>();

            db = "tbl_district";
            query = "SELECT * FROM " + db +" WHERE id_province = '" +params[1]+"'";

            Cursor cursor = this.database.rawQuery(query, null);
            try {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    int countstreet = cursor.getInt(2);
                   // String idProvince=cursor.getString(3);
                    //public District(String idDistrict, String titleDistrict, int numofStreet, boolean isSelected, String idProvince) {
                    District item = new District(id,name,countstreet,false);

                    listDistricts.add(item);
                }
            } finally {
                cursor.close();
                this.close();
            }
            list=listDistricts;

        } else if (params[0].equals(AppConfig.REQUEST_CODE_LIST_PROVINCE)) {
            this.open();
            List<Province> listProvince=new ArrayList<>();
            db = "tbl_province";
            query = "SELECT * FROM " + db ;

            Cursor cursor = this.database.rawQuery(query, null);
            try {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    //public District(String idDistrict, String titleDistrict, int numofStreet, boolean isSelected, String idProvince) {
                    Province item = new Province(id,name);
                    listProvince.add(item);
                }
            } finally {
                cursor.close();
                this.close();
            }
            list=listProvince;
        }
        return list;
    }
}
