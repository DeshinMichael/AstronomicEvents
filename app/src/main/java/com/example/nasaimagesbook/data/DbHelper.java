package com.example.nasaimagesbook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //Таблица с событиями
    public static final String TABLE_NAME = "events_table";
    public static final String _ID = "_id";
    public static final String IMAGE_URL = "image_url";
    public static final String NAME_OF_EVENT = "name_of_event";
    public static final String DESC_OF_EVENT = "desc_of_event";

    //База данных
    public static final String DB_NAME = "db_events.db";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;
    Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " +
                TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + IMAGE_URL + " TEXT," +
                NAME_OF_EVENT + " TEXT," + DESC_OF_EVENT + " TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    
}
