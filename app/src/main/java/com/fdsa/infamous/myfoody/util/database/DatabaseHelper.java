package com.fdsa.infamous.myfoody.util.database;

/**
 * Created by FDSA on 4/6/2017.
 */

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "myfoody.sqlite";
    private static final int DATABASE_VERSION = 1;

    //Hàm khởi tạo kết nối CSDL
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }

}