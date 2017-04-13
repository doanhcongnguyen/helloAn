package com.crud.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.crud.table.TablePerson;
import com.crud.table.TableStudent;

/**
 * Created by alpha on 4/11/2017.
 */

public class AlphaDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Alpha.db";
    private static final int DATABASE_VERSION = 1;

    public AlphaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TablePerson.CREATE_QUERY);
        db.execSQL(TableStudent.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TablePerson.DROP_QUERY);
        db.execSQL(TableStudent.DROP_QUERY);
        onCreate(db);
    }

    public boolean insertPerson(String name, String gender, int age) {
        SQLiteDatabase db = getWritableDatabase();
        return TablePerson.insertPerson(db, name, gender, age);
    }

    public boolean updatePerson(int id, String name, String gender, int age) {
        SQLiteDatabase db = getWritableDatabase();
        return TablePerson.updatePerson(db, id, name, gender, age);
    }

    public Integer deletePerson(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return TablePerson.deletePerson(db, id);
    }

    public Cursor getPerson(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return TablePerson.getPerson(db, id);
    }

    public Cursor getAllPersons() {
        SQLiteDatabase db = getWritableDatabase();
        return TablePerson.getAllPersons(db);
    }

    public boolean insertStudent(String name, int personId) {
        SQLiteDatabase db = getWritableDatabase();
        return TableStudent.insertStudent(db, name, personId);
    }

    public boolean updateStudent(int id, String name, int personId) {
        SQLiteDatabase db = getWritableDatabase();
        return TableStudent.updateStudent(db, id, name, personId);
    }

    public Integer deleteStudent(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return TableStudent.deleteStudent(db, id);
    }

    public Cursor getStudent(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return TableStudent.getStudent(db, id);
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = getWritableDatabase();
        return TableStudent.getAllStudents(db);
    }
}


