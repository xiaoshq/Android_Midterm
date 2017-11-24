package com.xiaoshq.sqltest;

/**
 * Created by ivan on 2017/11/22.
 */

public class Data {
    // id，姓名，头像，性别，籍贯，出生年，死亡年，主效势力，是否已喜爱，是否已删除
    public int id;
    public String name;
    public String img;
    public int sex;// 0->male, 1->female
    public String region;
    public String born;
    public String dead;
    public String master;
    public int isFavourite;// 是否加入了favourite  0->no, 1->yes
    public int isDeleted;// 是否加入了回收站  0->no, 1->yes

    public Data() {
    }

    public Data(int id, String name, String img, int sex, String region, String born,
                String dead, String master, int isFavourite, int isDeleted) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.sex = sex;
        this.region = region;
        this.born = born;
        this.dead = dead;
        this.master = master;
        this.isFavourite = isFavourite;
        this.isDeleted = isDeleted;
    }
}
