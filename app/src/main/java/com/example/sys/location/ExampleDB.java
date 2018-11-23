package com.example.sys.location;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExampleDB extends SQLiteOpenHelper {
    private static final String DATABASE = "sample_db";
    private static final String TABLENAME = "location";
    public static final String LONG = "LONG";
    public static final String LAT = "LAT";
    public ExampleDB(Context context) {
        super( context, DATABASE,null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "Create table " + TABLENAME + "(" + LONG + " TEXT," + LAT + " TEXT," + ")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS" + TABLENAME );
        onCreate( sqLiteDatabase );
    }
    public void insert(double lon, double lat) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( LONG, lon );
        contentValues.put(LAT, lat );
        sqLiteDatabase.insert( TABLENAME, null, contentValues );
    }

}
