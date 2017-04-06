package com.fdsa.infamous.myfoody.database;

/**
 * Created by FDSA on 4/6/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class DataAccess {
    private SQLiteOpenHelper openHelper;
    public SQLiteDatabase database;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    public DataAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */


    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    public abstract List<?> executeSelect(String... params);
    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */

}
