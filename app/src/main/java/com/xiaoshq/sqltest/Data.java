package com.xiaoshq.sqltest;

/**
 * Created by ivan on 2017/11/22.
 */

public class Data {
    // id，姓名，头像，性别，籍贯，出生年，死亡年，主效势力
    public int id;
    public String name;
    public int img;
    public int sex;
    public String region;
    public String born;
    public String dead;
    public String master;

    public Data() {
    }

    public Data(int id, String name, int img, int sex,
                String region, String born, String dead, String master) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.sex = sex;
        this.region = region;
        this.born = born;
        this.dead = dead;
        this.master = master;
    }
}
