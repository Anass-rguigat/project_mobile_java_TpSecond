package com.example.secondtp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Employe.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "my_employe";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_TELEPHONE = "telephone";
    private static final String COLUMN_EMAIL = "email";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOM + " TEXT, " +
                COLUMN_PRENOM + " TEXT, " +
                COLUMN_TELEPHONE + " TEXT, " +
                COLUMN_EMAIL + " TEXT);";
        Log.d("DATABASECREATE", "Creating table with query: " + query);
        db.execSQL(query);
        Log.d("DATABASECREATE", "Table created successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addEmploye(String nom, String prenom, String telephone, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOM, nom);
        cv.put(COLUMN_PRENOM, prenom);
        cv.put(COLUMN_TELEPHONE, telephone);
        cv.put(COLUMN_EMAIL, email);

        try {
            long result = db.insert(TABLE_NAME, null, cv);
            db.close();
            if (result == -1) {
                Log.e("DatabaseInsert", "Failed to insert data into database.");
                return false;
            } else {
                Log.i("DatabaseInsert", "Successfully inserted data into database.");
                return true;
            }
        } catch (Exception e) {
            Log.e("DatabaseInsert", "Error during insert operation: " + e.getMessage());
            return false;
        }
    }

    public List<HashMap<String, String>> getAllEmployes() {
        List<HashMap<String, String>> employesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                HashMap<String, String> employe = new HashMap<>();
                employe.put("nom", cursor.getString(cursor.getColumnIndex(COLUMN_NOM)));
                employe.put("prenom", cursor.getString(cursor.getColumnIndex(COLUMN_PRENOM)));
                employe.put("telephone", cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)));
                employe.put("email", cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                employesList.add(employe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return employesList;
    }
}
