package com.example.structuredplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName="Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table allusers( username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists allusers");
    }

    //Thêm dữ liệu username và password vào bảng allusers
    public Boolean insertData(String username, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = database.insert("allusers", null, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    //Kiểm tra username, nếu tồn tại thì true, không thì false
    public Boolean checkUsername(String username){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from allusers where username = ?", new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    //Kiểm tra đăng nhập
    public Boolean checkLogin(String username, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from allusers where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}
