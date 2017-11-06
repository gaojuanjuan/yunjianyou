package com.yunjy.jianyou.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zt
 */

public class DaoHelper extends OrmLiteSqliteOpenHelper {
    public DaoHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null,dbVersion);
    }

    public DaoHelper(Context context, String databaseName, int databaseVersion,
                     String configFile) {
        super(context, databaseName, null,databaseVersion,new File(configFile));
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {


    }

    Map<String, Dao> daos = new HashMap<String,Dao>();
    public synchronized Dao getDao(Class clazz) {
        String simpleName = clazz.getSimpleName();
        Dao dao = null;
        if(daos.containsKey(simpleName)){
            dao = daos.get(simpleName);
        }
        if(dao == null){
            try {
                dao = super.getDao(clazz);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            daos.put(simpleName,dao);
        }
        return dao;
    }

}
