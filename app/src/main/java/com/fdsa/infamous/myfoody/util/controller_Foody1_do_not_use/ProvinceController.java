package com.fdsa.infamous.myfoody.util.controller_Foody1_do_not_use;

import android.content.Context;
import android.database.Cursor;

import com.fdsa.infamous.myfoody.common.bean_F2.DistrictBean;
import com.fdsa.infamous.myfoody.common.bean_F2.ProvinceBean;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.util.database.DataAccess;

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
        this.context = context;
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
        List<?> list = new ArrayList<>();
        String query = "";
        String db = "";
        if (params[0].equals(AppConfig.REQUEST_CODE_LIST_AREA)) {
            this.open();
            List<DistrictBean> listDistrictBeen = new ArrayList<>();

            db = "tbl_district";
            query = "SELECT * FROM " + db + " WHERE id_province = '" + params[1] + "'";

            Cursor cursor = this.database.rawQuery(query, null);
            try {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    int countstreet = cursor.getInt(2);
                    // String idProvince=cursor.getString(3);
                    //public DistrictBean(String idDistrict, String titleDistrict, int numofStreet, boolean isSelected, String idProvince) {
                    DistrictBean item = new DistrictBean(id, name, countstreet, false);

                    listDistrictBeen.add(item);
                }
            } finally {
                cursor.close();
                this.close();
            }
            list = listDistrictBeen;

        } else if (params[0].equals(AppConfig.REQUEST_CODE_LIST_PROVINCE)) {
            this.open();
            List<ProvinceBean> listProvinceBean = new ArrayList<>();
            db = "tbl_province";
            query = "SELECT * FROM " + db;

            Cursor cursor = this.database.rawQuery(query, null);
            try {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    //public DistrictBean(String idDistrict, String titleDistrict, int numofStreet, boolean isSelected, String idProvince) {
                    ProvinceBean item = new ProvinceBean(id, name, null);
                    listProvinceBean.add(item);
                }
            } finally {
                cursor.close();
                this.close();
            }
            list = listProvinceBean;
        }
        return list;
    }
}
