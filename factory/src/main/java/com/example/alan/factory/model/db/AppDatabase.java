package com.example.alan.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * @author alan
 *         Date  2018/7/13.
 *         Function : 数据库信息
 *         Issue :
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;

}
