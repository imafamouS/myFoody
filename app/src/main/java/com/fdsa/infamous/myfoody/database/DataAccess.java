package com.fdsa.infamous.myfoody.database;

/**
 * Created by FDSA on 4/6/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public abstract class DataAccess {
    public SQLiteDatabase database;
    //145
    private SQLiteOpenHelper openHelper;

    //Hàm khởi tạo kết nối CSDL
    public DataAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    //Hàm thực hiện mở kết nối
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    //Hàm thực hiện đóng kết nối
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    //Hàm thực hiện câu lệnh query
    public abstract List<?> executeSelect(String... params);

}
