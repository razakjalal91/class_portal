package com.freelance.razak.classportal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Razak on 2/2/2017.
 */

public class DbHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "classportal.db";
    public static final String TABLE_STUDENTS = "student_table";

    public DbHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_STUDENTS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,STUDENTID TEXT,LECTURERID TEXT,LECTURERNAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_STUDENTS);
        onCreate(db);
    }
}
