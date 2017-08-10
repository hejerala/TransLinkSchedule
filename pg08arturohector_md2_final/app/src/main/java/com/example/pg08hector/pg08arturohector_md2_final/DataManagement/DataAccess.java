package com.example.pg08hector.pg08arturohector_md2_final.DataManagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pg08hector on 14/12/2016.
 */

public class DataAccess {
    private SQLiteDatabase database;
    private SQLiteManager dbManager;

    public DataAccess(Context context)
    {
        dbManager = new SQLiteManager(context);
    }

    public void openWrite() throws SQLException
    {
        database = dbManager.getWritableDatabase();
    }

    public void openRead() throws SQLException
    {
        database = dbManager.getReadableDatabase();
    }

    public void close()
    {
        dbManager.close();
    }

    /*********************************    Delete   *****************************************/

    public void DeleteStopRows()
    {
        try
        {
            database.delete(SQLiteManager.TABLE_STOP, "1", null);
        }
        catch(Exception ex)
        {
            int error=0;
            error++;
            error = error/3;
        }
    }

    public void DeleteStop(String stop)
    {
        try
        {
            database.delete(SQLiteManager.TABLE_STOP, SQLiteManager.COLUMN_STOP_NUMBER+"="+stop, null);
        }
        catch(Exception ex)
        {
            int error=0;
            error++;
            error = error/3;
        }
    }

    public void DeleteEverything()
    {
        DeleteStopRows();
    }

    /*********************************    Insert   *****************************************/

    public Boolean InsertStop(String stop)
    {
        ContentValues values = new ContentValues();
        //values.put(SQLiteManager.COLUMN_ID_STOP, user.getIduser());
        values.put(SQLiteManager.COLUMN_STOP_NUMBER, stop);

        if(database.insert(SQLiteManager.TABLE_STOP, null, values) > 0)
        {
            return true;
        }

        return false;
    }

    public void InsertEverything(ArrayList<String> stops)
    {
        DeleteEverything();

        for (String stop: stops) {
            InsertStop(stop);
        }
    }

    /*********************************    Select   *****************************************/

    public ArrayList<String> GetAllStops()
    {
        ArrayList<String> stops = new ArrayList<>();

        try
        {
            Cursor cursor = database.query(SQLiteManager.TABLE_STOP, null, null, null, null, null, null);

            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                while(!cursor.isAfterLast())
                {
                    String stop = cursor.getString(1);
                    stops.add(stop);
                    cursor.moveToNext();
                }
            }

            cursor.close();
            return stops;
        }
        catch(Exception ex)
        {
            return stops;
        }
    }
}
