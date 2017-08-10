package com.example.pg08hector.pg08arturohector_md2_final.DataManagement;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by pg08hector on 14/12/2016.
 */

public class SQLiteManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TranslinkOpen";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_STOP = "Stop";
    public static final String COLUMN_ID_STOP ="idstop";
    public static final String COLUMN_STOP_NUMBER = "number";

    public static final String CREATE_TABLE_STOP = "CREATE TABLE IF NOT EXISTS " + TABLE_STOP
            + " ("
            + COLUMN_ID_STOP + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_STOP_NUMBER + " TEXT NOT NULL"
            + ");";

    public SQLiteManager(Context c)
    {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void deleteEverything(SQLiteDatabase database)
    {
        try
        {
            database.execSQL("DELETE * FROM " + TABLE_STOP);
        }
        catch(SQLException EX)
        {
            int error = 0;
            error++;
            error = error/2;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        try
        {
            database.execSQL(CREATE_TABLE_STOP);
        }
        catch(SQLException EX)
        {
            int error = 0;
            error++;
            error = error/2;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(SQLiteManager.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOP);
        onCreate(db);
    }

}
