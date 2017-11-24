package com.xiaoshq.sqltest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ivan on 2017/11/22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "data_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建数据库后，对数据库的操作
        // data(id，姓名，头像，性别，籍贯，出生年，死亡年，主效势力)
        String sql = "create table if not exists data ";
        sql += "(id integer primary key autoincrement, name text, img text, ";
        sql += "sex integer, region text, born text, dead text, master text);";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 更改数据库版本的操作
        String sql = "DROP TABLE IF EXISTS data";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

}
