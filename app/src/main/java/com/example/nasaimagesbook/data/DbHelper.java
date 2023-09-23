package com.example.nasaimagesbook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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

    public void addFavEvent(String image, String name, String desc){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_URL, image);
        cv.put(NAME_OF_EVENT, name);
        cv.put(DESC_OF_EVENT, desc);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Добавлено успешно!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public Cursor readAllDataOfFavEvents(){
        String query = "SELECT * FROM " + TABLE_NAME;
        db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
