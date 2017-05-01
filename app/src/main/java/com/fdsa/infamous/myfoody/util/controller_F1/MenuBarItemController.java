package com.fdsa.infamous.myfoody.util.controller_F1;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.fdsa.infamous.myfoody.common.bean_F2.MenuBarItemBean;
import com.fdsa.infamous.myfoody.config.AppConfig;
import com.fdsa.infamous.myfoody.util.database.DataAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/6/2017.
 */

public class MenuBarItemController extends DataAccess {

    private final static String TAG;

    static {
        TAG = "MenuBarController";
    }

    Context context;
    private DataAccess dataAccess;

    //Hàm khởi tạoo
    public MenuBarItemController(Context context) {
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

    //Hàm thực hiện việc lấy danh sách các danh mục (Tab Danh mục)
    @Override
    public List<?> executeSelect(String... params) {
        List<MenuBarItemBean> list = new ArrayList<>();

        String query;
        String db = "";
        if (params[0].equals(AppConfig.REQUEST_CODE_CATEGORY_WHAT2DO)) {
            db = "tbl_restype";

        } else if (params[0].equals(AppConfig.REQUEST_CODE_CATEGORY_WHERE2GO)) {
            db = "tbl_wheretype";
        }
        this.open();
        query = "SELECT * FROM " + db;
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String img = cursor.getString(2);
                MenuBarItemBean item = new MenuBarItemBean(id, title, img, false);
                list.add(item);
            }
        } finally {
            cursor.close();
            this.close();
        }
        Log.d(TAG, "Get list ok");
        return list;
    }

    public List<MenuBarItemBean> getMenubarItem(String code) {
        return (List<MenuBarItemBean>) executeSelect(code);
    }
}
