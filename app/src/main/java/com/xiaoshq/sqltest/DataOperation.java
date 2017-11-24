package com.xiaoshq.sqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 2017/11/22.
 */

public class DataOperation {
    private static final String[] tableColumns = new String[] {"id","name","img",
            "sex","region","born","dead","master"};
    private Context context;
    private DatabaseHelper databaseHelper;

    public DataOperation(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    // 判断表中是否有数据
    public boolean isDataExist() {
        int count = 0;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = databaseHelper.getReadableDatabase();
            cursor = db.query("data", new String[]{"COUNT(id)"}, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        } catch (Exception e) {
            Log.e("operate","",e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
        return false;
    }

    // 初始化数据
    public void initTable() {
        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
            db.beginTransaction();

            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(1,'p1',1,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(2,'p2',2,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(3,'p3',3,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(4,'p4',4,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(5,'p5',5,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(6,'p6',6,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(7,'p7',7,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(8,'p8',8,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(9,'p9',9,1,'CN',1000,1100,'CN');");
            db.execSQL("insert into data (id,name,img,sex,region,born,dead,master) values(10,'p10',10,1,'CN',1000,1100,'CN');");

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("operate","",e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    // 获取表中所有数据
    public List<Data> getAllData() {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = databaseHelper.getReadableDatabase();
            //select * from table data
            cursor = db.query("data",tableColumns,null,null,null,null,null);
            if (cursor.getCount() > 0) {
                List<Data> dataList = new ArrayList<Data>(cursor.getCount());
                while (cursor.moveToNext()) {
                    dataList.add(toData(cursor));
                }
                return dataList;
            }
        } catch (Exception e) {
            Log.e("operate","",e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) cursor.close();
        }
        return null;
    }

    // 根据id查询数据库中的数据
    public Data getDataById(int id) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        String idStr = Integer.toString(id);
        try {
            db = databaseHelper.getReadableDatabase();
            // select * from table data where id = 'xxx'
            cursor = db.rawQuery("SELECT * FROM data WHERE id=?",new String[]{idStr});
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    return toData(cursor);
                }
            }
        } catch (Exception e) {
            Log.e("operate","",e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
        return null;
    }

    // 根据名字查询数据库中的数据
    public Data getDataByName(String name) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = databaseHelper.getReadableDatabase();
            // select * from table data where name = 'xxx'
            cursor = db.rawQuery("SELECT * FROM data WHERE name=?",new String[]{name});
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    return toData(cursor);
                }
            }
        } catch (Exception e) {
            Log.e("operate","",e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
        return null;
    }

    // 将查找到的数据转换成Data类
    public Data toData(Cursor cursor) {
        Data data = new Data();
        data.id = (cursor.getInt(cursor.getColumnIndex("id")));
        data.name = (cursor.getString(cursor.getColumnIndex("name")));
        data.img = (cursor.getInt(cursor.getColumnIndex("img")));
        data.sex = (cursor.getInt(cursor.getColumnIndex("sex")));
        data.region = (cursor.getString(cursor.getColumnIndex("region")));
        data.born = (cursor.getString(cursor.getColumnIndex("born")));
        data.dead = (cursor.getString(cursor.getColumnIndex("dead")));
        data.master = (cursor.getString(cursor.getColumnIndex("master")));
        return data;
    }

    // 根据id修改数据为data里的新数据
    public boolean updateData(int id, Data data) {
        SQLiteDatabase db = null;
        String idStr = Integer.toString(id);
        try {
            db = databaseHelper.getWritableDatabase();
            db.beginTransaction();

            ContentValues cv = new ContentValues();
            cv.put("name",data.name);
            cv.put("sex",data.sex);
            cv.put("img",data.img);
            cv.put("region",data.region);
            cv.put("born",data.born);
            cv.put("dead",data.dead);
            cv.put("master",data.master);
            db.update("data",cv,"id = ?",new String[]{idStr});

            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e("operate","",e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    // 增加一条记录data到数据库里
    public boolean insertData(Data data) {
        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
            db.beginTransaction();

            ContentValues cv = new ContentValues();
            cv.put("name",data.name);
            cv.put("sex",data.sex);
            cv.put("img",data.img);
            cv.put("region",data.region);
            cv.put("born",data.born);
            cv.put("dead",data.dead);
            cv.put("master",data.master);
            db.insertOrThrow("data",null,cv);

            db.setTransactionSuccessful();
            return true;
        } catch (SQLiteConstraintException e){
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Log.e("operate","",e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    // 根据id删除一条记录
    public boolean deleteData(int id) {
        SQLiteDatabase db = null;
        String idStr = Integer.toString(id);
        try {
            db = databaseHelper.getWritableDatabase();
            db.beginTransaction();
            db.delete("data","id = ?",new String[]{idStr});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e("operate", "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

}
