package com.itcanteen.mysql.listenter;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.itcanteen.kafka.MysqlBinLogProducer;
import com.itcanteen.mysql.TemplateHolder;
import com.itcanteen.mysql.vo.BinLogKafkaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.util.parsing.json.JSON;


import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/11 10:55
 */

@Component
public class MySqlBinLogListener implements BinaryLogClient.EventListener {


    static final ThreadLocal<BinLogKafkaData> sThreadLocal = new ThreadLocal<BinLogKafkaData>();

    @Autowired
    TemplateHolder templateHolder;

    @Autowired
    MysqlBinLogProducer mysqlBinLogProducer;


    static BinLogKafkaData binLogKafkaData = new BinLogKafkaData();


    private String tableName;
    private String dbName;

    @Override
    public void onEvent(Event event) {
        EventType eventType = event.getHeader().getEventType();
        System.out.println(eventType.toString());
        if(eventType == EventType.TABLE_MAP){
            TableMapEventData data = event.getData();

            System.out.println(data.getTable());
            System.out.println(data.getDatabase());
           tableName =  data.getTable();
            dbName =  data.getDatabase();
            binLogKafkaData.setTableName(tableName);
            return ;
        }

        if(eventType!=EventType.EXT_UPDATE_ROWS
                &&eventType!=EventType.EXT_WRITE_ROWS
                &&eventType!=EventType.EXT_DELETE_ROWS){
            return ;
        }


        templateHolder.loadMeta(dbName,tableName);

        buildRowData(event.getData());

       String message =  com.alibaba.fastjson.JSON.toJSONString(binLogKafkaData);

        mysqlBinLogProducer.send(message);




    }


    private List<Serializable[]> getAfterValues(EventData eventData){
        if(eventData instanceof WriteRowsEventData){
            binLogKafkaData.setEventType("WriteRowsEventData");
           return  ((WriteRowsEventData) eventData).getRows();
        }
        if(eventData instanceof  UpdateRowsEventData){
            binLogKafkaData.setEventType("UpdateRowsEventData");
           return  ((UpdateRowsEventData) eventData).getRows().stream()
                   .map(Map.Entry::getValue)
                   .collect(Collectors.toList());
        }

        if(eventData instanceof  DeleteRowsEventData){
            binLogKafkaData.setEventType("DeleteRowsEventData");
           return  ((DeleteRowsEventData) eventData).getRows();
        }

        return Collections.emptyList();

    }

    private void buildRowData(EventData eventData){

        List<Map<String, String>> afterMapList = new ArrayList<>();

        Map<Integer, String> posMap = TemplateHolder.posMap;

        for(Serializable[] after:getAfterValues(eventData)){
            Map<String,String> afterMap = new HashMap<>();
            for(int ix=0;ix<after.length;++ix){
               //列名
                String colName = posMap.get(ix);

                if(null==colName){
                    continue;
                }

               String colVlaue =  after[ix].toString();
                afterMap.put(colName,colVlaue);
            }

            afterMapList.add(afterMap);
            binLogKafkaData.setAfter(afterMapList);
        }

        System.out.println(binLogKafkaData.toString());

    }
}
