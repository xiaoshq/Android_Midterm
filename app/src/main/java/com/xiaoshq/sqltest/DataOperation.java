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

    // 初始化数据(10个人物)
    public void initTable() {
        SQLiteDatabase db = null;
        try {
            db = databaseHelper.getWritableDatabase();
            db.beginTransaction();

            String str1 = "insert into data (id,name,img,sex,region,born,dead,master) values";

            String info1 = "曹操是西园八校尉之一，曾只身行刺董卓，失败后和袁绍共同联合天下诸侯讨伐董卓，后独自发展自身势力，一生中先后战胜了袁术、吕布、张绣、袁绍、刘表、张鲁、马超等割据势力，统一了北方。但是在南下讨伐江东的战役中，曹操在赤壁惨败。后来在和蜀汉的汉中争夺战中，曹操再次无功而返。曹操一生未称帝，他病死后，曹丕继位后不久称帝，追封曹操为魏武皇帝。";
            String info2 = "刘备，蜀汉的开国皇帝，汉景帝之子中山靖王刘胜的后代。刘备少年孤贫，以贩鞋织草席为生。黄巾起义时，刘备与关羽、张飞桃园结义，成为异姓兄弟，一同剿除黄巾，有功，任安喜县尉，不久辞官；董卓乱政之际，刘备随公孙瓒讨伐董卓，三人在虎牢关战败吕布。后诸侯割据，刘备势力弱小，经常寄人篱下，先后投靠过公孙瓒、曹操、袁绍、刘表等人，几经波折，却仍无自己的地盘。赤壁之战前夕，刘备在荆州三顾茅庐，请诸葛亮出山辅助，在赤壁之战中，联合孙权打败曹操，奠定了三分天下的基础。刘备在诸葛亮的帮助下占领荆州，不久又进兵益州，夺取汉中，建立了横跨荆益两州的政权。后关羽战死，荆州被孙权夺取，刘备大怒，于称帝后伐吴，在夷陵之战中为陆逊用火攻打得大败，不久病逝于白帝城，临终托孤于诸葛亮。";
            String info3 = "偏将军、南郡太守。自幼与孙策交好，策离袁术讨江东，瑜引兵从之。为中郎将，孙策相待甚厚，又同娶二乔。策临终，嘱弟权曰：“外事不决，可问周瑜”。瑜奔丧还吴，与张昭共佐权，并荐鲁肃等，掌军政大事。赤壁战前，瑜自鄱阳归。力主战曹，后于群英会戏蒋干、怒打黄盖行诈降计、后火烧曹军，大败之。后下南郡与曹仁相持，中箭负伤，与诸葛亮较智斗，定假涂灭虢等计，皆为亮破，后气死于巴陵，年三十六岁。临终，上书荐鲁肃代其位，权为其素服吊丧。";
            String info4 = "人称卧龙先生，有经天纬地之才，鬼神不测之机。刘皇叔三顾茅庐，遂允出山相助。曾舌战群儒、借东风、智算华容、三气周瑜，辅佐刘备于赤壁之战大败曹操，更取得荆州为基本。后奉命率军入川，于定军山智激老黄忠，斩杀夏侯渊，败走曹操，夺取汉中。刘备伐吴失败，受遗诏托孤，安居平五路，七纵平蛮，六出祁山，鞠躬尽瘁，死而后已。其手摇羽扇，运筹帷幄的潇洒形象，千百年来已成为人们心中“智慧”的代名词。";
            String info5 = "司马昭随父司马懿抵御诸葛亮伐魏。多有军识。司马师死后，司马昭为大将军。手握兵权，专揽国政，并阴谋代魏。甘露五年，杀魏帝曹髦，另立曹奂为帝。景元四年，司马昭分兵伐蜀。蜀灭亡后，钟会阴谋造反，司马昭率领大军亲讨。未至，钟会业已败亡。回朝后自称晋公，后加晋王。立子炎为世子。司马昭责戏阿斗，阿斗蜀乐不动情。咸熙二年，昭中风猝死。数月后，子司马炎代魏称帝。建晋朝。追尊昭为文帝，庙号太祖。";
            String info6 = "孙权19岁就继承了其兄孙策之位，力据江东，击败了黄祖。后东吴联合刘备，在赤壁大战击溃了曹操军。东吴后来又和曹操军在合肥附近鏖战，并从刘备手中夺回荆州、杀死关羽、大破刘备的讨伐军。曹丕称帝后孙权先向北方称臣，后自己建吴称帝，迁都建业。";
            String info7 = "旧从袁绍，因知袁绍终不能成大事遂与其侄荀攸投奔曹操，与曹操长谈后被称为“吾之子房也”。曹操攻打陶谦时吕布袭取了兖州，荀彧与程昱保住了三座城池。荀彧为曹操出过很多重要的战略谋划，如以兖州为基地、二虎竞食和驱虎吞狼、迎汉献帝往许都、开玄武湖练水军等等，立有大功。荀彧后为汉侍中、尚书令，参与国家大事並经常留守许都。袁绍势力强大，曹操对是否与他开战而犹豫不决，荀彧就用四胜四败之说开导曹操，使他终下决心抗袁。官渡之战筹划粮草供给及回信坚定了曹操的意志，最后得已击败袁绍统一中原，这都是荀彧的计划。时董昭劝曹操称魏公，荀彧表示反对，曹操深感不满，遂将其招入军中，从征孙权。荀彧因病留于寿春，不久在曹操的暗示下服毒自杀。";
            String info8 = "刘备之军师中郎将。统号凤雏，与诸葛亮齐名。赤壁之战，统避乱江东，为鲁肃荐於周瑜，入曹营献连环计，助瑜火攻大败。瑜卒，亮往吊孝，因得见统。时肃亦荐统，因统貌陋，慢孙权，不用。统遂往荆州投先主，先主亦因貌轻之，任为耒阳令。统到任，不理政事，先主怒遣张飞往责，飞识统才，遂拜统为副军师中郎将，与亮共赞方略，教练军士。后统随先主取蜀，设计斩杀杨怀、高沛，得涪水关。攻雒城，因疑亮欲争功，不从亮书劝阻，进至落凤坡，中张任埋伏，为乱箭射死。";
            String info9 = "在争夺继承权问题上处心积虑，战胜了文才更胜一筹的弟弟曹植，被立为王世子。曹操逝世后，曹丕继位成为魏王，以不参加葬礼之罪逼弟弟曹植写下七步诗，险些将其杀害，又顺利夺下弟弟曹彰的兵权，坐稳了魏王之位。不久，曹丕逼汉献帝让位，代汉称帝，为魏国开国皇帝。刘备伐吴时，曹丕看出刘备要失败，但不听谋士之言，偏要坐山观虎斗，事后又起兵伐吴，结果被徐盛火攻击败。回洛阳后，曹丕大病，临终前托付曹睿给曹真、司马懿等人，终年四十岁。";
            String info10 = "先主领荆州，伊籍荐良。良至，先主优礼相待，请问保守荆襄之策。与语大悦，遂用良为从事。及先主伐蜀地，军师亮特遣良奉书报之庞统凶兆。后统亡，亮往蜀地，留良等助关羽。后吴都督鲁肃设宴请羽，良以有诈谏之，不从。及先主为汉中王，羽讨魏，以良、伊籍为参谋，一同征进。羽臂中毒箭，名医华佗为之刮骨，羽与良弈棋。后荆州失，羽差良、伊籍赍文三道，星夜赴成都求救。先主践祚，起大军伐吴，以良、陈震掌理文书。及吴中仇人尽诛，良奏请回，先主不从。吴以陆逊为督，良奏先主需提防，又不从。吴军坚守不出，良奏其必欲待蜀军之变。后先主下寨，良劝画图本报与丞相亮。后得亮令，火速投御营来，然军已败。先主崩，南蛮叛乱，会良卒。";

            db.execSQL(str1+"(1, '曹操','/sdcard/caocao.png',0,'豫州沛国谯',155,220,'"+info1+"');");
            db.execSQL(str1+"(2, '刘备','/sdcard/liubei.png',0,'幽州涿郡涿',161,223,'"+info2+"');");
            db.execSQL(str1+"(3, '周瑜','/sdcard/zhouyu.png',0,'扬州庐江郡舒',175,210,'"+info3+"');");
            db.execSQL(str1+"(4, '诸葛亮','/sdcard/zhugeliang.png',0,'徐州琅邪国阳都',181,234,'"+info4+"');");
            db.execSQL(str1+"(5, '司马昭','/sdcard/simazhao.png',0,'司隶河内郡温',211,265,'"+info5+"');");
            db.execSQL(str1+"(6, '孙权','/sdcard/sunquan.png',0,'扬州吴郡富春',182,252,'"+info6+"');");
            db.execSQL(str1+"(7, '荀彧','/sdcard/xunyu.png',0,'豫州颍川郡颍阴',163,212,'"+info7+"');");
            db.execSQL(str1+"(8, '庞统','/sdcard/pangtong.png',0,'荆州南郡襄阳',179,214,'"+info8+"');");
            db.execSQL(str1+"(9, '曹丕','/sdcard/caopi.png',0,'豫州沛国谯',187,226,'"+info9+"');");
            db.execSQL(str1+"(10,'马良','/sdcard/maliang.png',0,'荆州南郡宜城',187,222,'"+info10+"');");

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
        data.img = (cursor.getString(cursor.getColumnIndex("img")));
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
