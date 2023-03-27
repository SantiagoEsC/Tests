package com.example.corte2taller1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="login.db";
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL("Create table usuarios(usuario TEXT primary key, contraseña TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("drop table if exists usuarios");
    }

    public Boolean insertData(String usuario, String contraseña){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("usuario", usuario);
        values.put("contraseña", contraseña);

        long result = db.insert("usuarios", null, values);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean checkusername(String usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios where usuario=?", new String[]{usuario});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
    public Boolean checkusernamepassword(String usuario, String contraseña){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios where usuario=? and contraseña=?", new String[]{usuario, contraseña});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }


}


