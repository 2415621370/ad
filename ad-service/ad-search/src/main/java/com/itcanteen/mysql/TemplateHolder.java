package com.itcanteen.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/11 11:25
 */

@Component
public class TemplateHolder {

    private String SQL_SCHEMA = "SELECT " +
            "TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,ORDINAL_POSITION " +
            "FROM " +
            "information_schema.COLUMNS " +
            "WHERE " +
            "TABLE_SCHEMA=? " +
            "AND " +
            "TABLE_NAME=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public  static Map<Integer,String> posMap = new HashMap<>();

    public void loadMeta(String dataBase,String tableName){
        jdbcTemplate.query(SQL_SCHEMA,new Object[]{
                dataBase,tableName
        },(rs,i)->{
           int pos =  rs.getInt("ORDINAL_POSITION");
            String colName =  rs.getString("COLUMN_NAME");
            posMap.put(pos-1,colName);

            return null;
        });
    }


}
