package com.example.secondtp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DBEMPLOYES";
    private static final int DB_VERSION = 1;

    private static final String ID_EMPLOYE = "idEmploye";
    private static final String NOM_EMPLOYE = "nomEmploye";
    private static final String PRENOM_EMPLOYE = "prenomEmploye";
    private static final String TELEPHONE_EMPLOYE = "telephoneEmploye";
    private static final String EMAIL_EMPLOYE = "emailEmploye";

    private static final String TABLE_EMPLOYES = "EMPLOYES";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_EMPLOYES + " (" +
                ID_EMPLOYE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOM_EMPLOYE + " TEXT, " +
                PRENOM_EMPLOYE + " TEXT, " +
                TELEPHONE_EMPLOYE + " TEXT, " +
                EMAIL_EMPLOYE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYES);
        onCreate(db);
    }

    public void addEmploye(Employe employe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOM_EMPLOYE, employe.getNomEmploye());
        values.put(PRENOM_EMPLOYE, employe.getPrenomEmploye());
        values.put(TELEPHONE_EMPLOYE, employe.getTelephoneEmploye());
        values.put(EMAIL_EMPLOYE, employe.getEmailEmploye());
        db.insert(TABLE_EMPLOYES, null, values);
        db.close();
    }

    public Employe getEmployeByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMPLOYES,
                new String[]{ID_EMPLOYE, NOM_EMPLOYE, PRENOM_EMPLOYE, TELEPHONE_EMPLOYE, EMAIL_EMPLOYE},
                ID_EMPLOYE + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Employe employe = new Employe();
            employe.setIdEmploye(cursor.getInt(0));
            employe.setNomEmploye(cursor.getString(1));
            employe.setPrenomEmploye(cursor.getString(2));
            employe.setTelephoneEmploye(cursor.getString(3));
            employe.setEmailEmploye(cursor.getString(4));
            cursor.close();
            return employe;
        }
        return null;
    }

    public List<Employe> getAllEmployes() {
        List<Employe> employes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EMPLOYES, null);
        if (cursor.moveToFirst()) {
            do {
                Employe employe = new Employe();
                employe.setIdEmploye(cursor.getInt(0));
                employe.setNomEmploye(cursor.getString(1));
                employe.setPrenomEmploye(cursor.getString(2));
                employe.setTelephoneEmploye(cursor.getString(3));
                employe.setEmailEmploye(cursor.getString(4));
                employes.add(employe);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return employes;
    }

    public void deleteEmploye(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYES, ID_EMPLOYE + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateEmploye(Employe employe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOM_EMPLOYE, employe.getNomEmploye());
        values.put(PRENOM_EMPLOYE, employe.getPrenomEmploye());
        values.put(TELEPHONE_EMPLOYE, employe.getTelephoneEmploye());
        values.put(EMAIL_EMPLOYE, employe.getEmailEmploye());
        db.update(TABLE_EMPLOYES, values, ID_EMPLOYE + "=?", new String[]{String.valueOf(employe.getIdEmploye())});
        db.close();
    }
}
