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

    public DataAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    public abstract List<?> executeSelect(String... params);

}
