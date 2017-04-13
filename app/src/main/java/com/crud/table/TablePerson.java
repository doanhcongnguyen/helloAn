package com.crud.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alpha on 4/13/2017.
 */

public class TablePerson {

    public static final String TABLE_NAME = "person";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_AGE = "age";

    public static final String CREATE_QUERY = " CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_GENDER + " TEXT, " +
                    COLUMN_AGE + " INTEGER)";

    public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static boolean insertPerson(SQLiteDatabase db, String name, String gender, int age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_AGE, age);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public static boolean updatePerson(SQLiteDatabase db, int id, String name, String gender, int age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_AGE, age);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{Integer.toString(id)});
        return true;
    }

    public static Integer deletePerson(SQLiteDatabase db, int id) {
        String WHERE_CLAUSE = COLUMN_ID + " = ? ";
        String[] params = {Integer.toString(id)};
        return db.delete(TABLE_NAME, WHERE_CLAUSE, params);
    }


    public static Cursor getPerson(SQLiteDatabase db, int id) {
        String query = "SELECT * "
                + " FROM " + TABLE_NAME
                + " WHERE " + COLUMN_ID + " = ?";
        String[] params = new String[] {Integer.toString(id)};
        Cursor res = db.rawQuery(query, params);
        return res;
    }

    public static Cursor getAllPersons(SQLiteDatabase db) {
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public static long getNumberOfRows(SQLiteDatabase db) {
        return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }
}
