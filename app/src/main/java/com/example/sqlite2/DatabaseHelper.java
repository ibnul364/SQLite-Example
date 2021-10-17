package com.example.sqlite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME = "students_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "NAME";
    public static final String COLUMN_3 = "SURNAME";
    public static final String COLUMN_4 = "MARKS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT, MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name,String surName, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_2,name);
        values.put(COLUMN_3,surName);
        values.put(COLUMN_4,marks);

       long result = db.insert(TABLE_NAME,null,values);
       if(result==-1){
           return false;
       }else{
           return true;
       }

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String surName, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_1,id);
        values.put(COLUMN_2,name);
        values.put(COLUMN_3,surName);
        values.put(COLUMN_4,marks);
        db.update(TABLE_NAME,values,"ID = ? ",new String[] {id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ? ",new String[] {id});

    }


}
