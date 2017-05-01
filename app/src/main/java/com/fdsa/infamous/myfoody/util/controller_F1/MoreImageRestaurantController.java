package com.fdsa.infamous.myfoody.util.controller_F1;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.fdsa.infamous.myfoody.util.database.DataAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FDSA on 4/8/2017.
 */

public class MoreImageRestaurantController extends DataAccess {

    static String TAG;

    static {
        TAG = "MoreImageResController";
    }

    //Hàm khởi tạo
    public MoreImageRestaurantController(Context context) {
        super(context);
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

    @Override
    public List<?> executeSelect(String... params) {
        return null;
    }

    //Hàm lấy thêm các ảnh của nhà hàng
    public List<byte[]> getListMoreImage(String res_id) {
        List<byte[]> list = new ArrayList<>();
        this.open();
        String db = "tbl_moreImageRes";
        String query = "SELECT * FROM " + db + " WHERE res_id ='" + res_id + "'";
        Cursor cursor = this.database.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                byte[] img = cursor.getBlob(1);
                list.add(img);
            }
        } finally {
            cursor.close();
            this.close();
        }
        Log.d(TAG, "get list ok");

        return list;
    }
}
