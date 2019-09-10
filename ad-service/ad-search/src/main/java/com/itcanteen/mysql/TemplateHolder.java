package com.itcanteen.mysql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/10 13:53
 */
@Component
@Slf4j
public class TemplateHolder {

    private String SQL_SCHEMA = "select table_schema, table_name, " +
            "column_name, ordinal_position from information_schema.columns " +
            "where table_schema = ? and table_name = ?";


    /**
     * 字段索引 -> 字段名
     * */
    public static Map<Integer, String> posMap = new HashMap<>();

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void loadMeta(String dataBase,String tableName){
        jdbcTemplate.query(SQL_SCHEMA, new Object[]{
                dataBase,tableName
        }, (rs, i) -> {

            int pos = rs.getInt("ORDINAL_POSITION");
            String colName = rs.getString("COLUMN_NAME");

             posMap.put(pos-1,colName);
            log.info(posMap.toString());
            return null;


           // return null;
        });
    }
}
