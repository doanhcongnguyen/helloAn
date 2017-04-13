package com.crud.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alpha on 4/13/2017.
 */

public class TableStudent {

    public static final String TABLE_NAME = "student";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PERSON_ID = "person_id";

    public static final String CREATE_QUERY = " CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_PERSON_ID + " INTEGER)";

    public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static boolean insertStudent(SQLiteDatabase db, String name, int personId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PERSON_ID, personId);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public static boolean updateStudent(SQLiteDatabase db, int id, String name, int personId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PERSON_ID, personId);
        db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?",
                new String[]{Integer.toString(id)});
        return true;
    }

    public static Integer deleteStudent(SQLiteDatabase db, int id) {
        String WHERE_CLAUSE = COLUMN_ID + " = ? ";
        String[] params = {Integer.toString(id)};
        return db.delete(TABLE_NAME, WHERE_CLAUSE, params);
    }


    public static Cursor getStudent(SQLiteDatabase db, int id) {
        String query = "SELECT * "
                + " FROM " + TABLE_NAME
                + " WHERE " + COLUMN_ID + " = ?";
        String[] params = new String[] {Integer.toString(id)};
        Cursor res = db.rawQuery(query, params);
        return res;
    }

    public static Cursor getAllStudents(SQLiteDatabase db) {
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }
}
