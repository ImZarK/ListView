package com.example.listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PersonajesDB";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_PERSONAJES = "personajes";
    private static final String KEY_ID = "id";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_IMAGEN = "imagen";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_PERSONAJES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOMBRE + " TEXT,"
                + KEY_IMAGEN + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAJES);
        onCreate(db);
    }

    public void insertPersonaje(String nombre, int imagen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, nombre);
        values.put(KEY_IMAGEN, imagen);
        db.insert(TABLE_PERSONAJES, null, values);
        db.close();
    }

    public void deleteAllPersonajes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSONAJES, null, null);
        db.close();
    }

    public Cursor getAllPersonajes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PERSONAJES, null);
    }
}
