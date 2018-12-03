package com.example.jiaguoqiang2018123;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context){
        MySqlite mySqlite = new MySqlite(context);

        db = mySqlite.getWritableDatabase();
    }
    //添加
    public long insert(String table, String nullColumnHack, ContentValues values){
        return db.insert(table, nullColumnHack, values);
    }
    //删除
    public long delete(String table, String whereClause, String[] whereArgs){
        return db.delete(table, whereClause, whereArgs);
    }

    //查询

    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy){
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}
