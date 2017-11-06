package com.yunjy.jianyou.db;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;
import com.yunjy.jianyou.ConfigGlobal;
import com.yunjy.jianyou.tools.LogUtils;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zt
 */

public abstract class BaseDBProvider<T> {

    Dao<T,Integer> mdao;

    public BaseDBProvider(){
        if(getTableClazz() == null){
          throw  new RuntimeException(" getTableClazz is null !");
        }
        createOrUPdataTable();
    }

    public void  createOrUPdataTable(){
        try {
            ConnectionSource connectionSource = ConfigGlobal.mDaoHelper.getConnectionSource();
            DatabaseConnection readWriteConnection = connectionSource.getReadWriteConnection(getTableName());
            boolean tableExists = readWriteConnection.isTableExists(getTableName());
            if(tableExists){
                Dao dao = DaoManager.createDao(connectionSource, getTableClazz());
                GenericRawResults genericRawResults = dao.queryRaw("select * from " + getTableName() + " limit ?", "0");
                String[] columnNames = genericRawResults.getColumnNames();
                Field[] declaredFields = getTableClazz().getDeclaredFields();
                for (Field field:declaredFields) {
                    DatabaseField annotation = field.getAnnotation(DatabaseField.class);
                    if(annotation == null  ){
                        Log.i("ztengDB",field.getName()+" DataBaseField is null ");
                        continue;
                    }
                    String s = annotation.columnName();
                    if(!hasColumn(columnNames,s)){
                        if(!TextUtils.isEmpty(s)){
                            LogUtils.i("rns_DB"," alter table "+getTableName()+" add "+s+" "+getDataType(field));
                            dao.executeRawNoArgs("ALTER TABLE "+getTableName()+" ADD "+s+" "+getDataType(field));
                        }
                    }
                }

            }else{
                TableUtils.createTableIfNotExists(connectionSource,getTableClazz());
            }
            mdao = DaoManager.createDao(connectionSource, getTableClazz());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public String getDataType(Field field){
        Class<?> type = field.getType();
        if(type == String.class){
            return "varchar";
        }else if (type == Integer.class){
            return "int";
        }else if (type == Long.class){
            return "long";
        }
        return "varchar";
    }



    public static boolean  hasColumn(String[]  columnNames, String s ) {
        for (String columnName:columnNames) {
            if(columnName.equals(s)){
                return true;
            }
        }
        return false;
    }


    /**
     *
     * @param t
     * @return id
     * @throws SQLException
     */
    public int addOne(T t) throws SQLException {
       return mdao.create(t);
    }

    public void addOrUpdate(T t) throws SQLException {
        mdao.createOrUpdate(t);
    }

    public List<T> getAll() throws SQLException {
        return mdao.queryForAll();
    }

    public abstract @NonNull
    Class getTableClazz();

    public abstract @NonNull
    String getTableName();

}
