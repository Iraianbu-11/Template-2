package com.example.template2.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_NAME = "users_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PHONE";
    public DBHelper(Context context) {
        super(context,  DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table users_table (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , EMAIL TEXT , PHONE TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists users_table");
    }

    public boolean insertData(String name , String email ,String phone){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4,phone);
        long result=DB.insert("users_table", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateData(String name, String email, String phone)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_3, email);
        contentValues.put(COL_4,phone);
        Cursor cursor = DB.rawQuery("Select * from users_table where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update(TABLE_NAME, contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteUser(String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users_table where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete(TABLE_NAME, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getUsers()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from users_table",null);
        return cursor;
    }



}
