package com.itcanteen.mysql.listenter;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.itcanteen.mysql.TemplateHolder;
import com.itcanteen.mysql.vo.BinLogKafkaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/9 17:47
 */
@Component
@Slf4j
public class MySqlBinLogListener implements BinaryLogClient.EventListener {

     @Autowired
    TemplateHolder templateHolder;
    @Override
    public void onEvent(Event event) {
        EventType type = event.getHeader().getEventType();


        if (type == EventType.TABLE_MAP){
           // log.info("------32222222222--------");
            TableMapEventData data = event.getData();
            String tableName = data.getTable();
            log.info(tableName);
             String dbName = data.getDatabase();
            templateHolder.loadMeta(dbName,tableName);

            BinLogKafkaData rowData = buildRowData(tableName,event.getData());
            if (rowData == null) {
                return;
            }

            rowData.setEventType("");

          /*   log.info(dbName);
            byte[] columnTypes = data.getColumnTypes();
           System.out.println(columnTypes);
            int[] columnMetadata = data.getColumnMetadata();
            System.out.println(columnMetadata);*/


          log.info("YYYYYYYYYYYYY",rowData.toString());
        }


    }


    private List<Serializable[]> getAfterValues(EventData eventData) {

        if (eventData instanceof WriteRowsEventData) {
            return ((WriteRowsEventData) eventData).getRows();
        }

        if (eventData instanceof UpdateRowsEventData) {
            return ((UpdateRowsEventData) eventData).getRows().stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }

        if (eventData instanceof DeleteRowsEventData) {
            return ((DeleteRowsEventData) eventData).getRows();
        }

        return Collections.emptyList();
    }


    private BinLogKafkaData buildRowData(String tableName,EventData eventData) {
        log.info("eventData->",eventData);

        if (eventData instanceof WriteRowsEventData) {
            List<Map<String, String>> afterMapList = new ArrayList<>();
            List<Serializable[]> rows = ((WriteRowsEventData) eventData).getRows();
            log.info("rows->length-{}",rows);
            for(Serializable[] after : rows){
                Map<String, String> afterMap = new HashMap<>();

                int colLen = after.length;

                for (int ix = 0; ix < colLen; ++ix) {
// 取出当前位置对应的列名
                    String colName = TemplateHolder.posMap.get(ix);
                    String colValue = after[ix].toString();
                    System.out.println(colValue);
                    afterMap.put(colName, colValue);
                }

                log.info(afterMap.toString());

                afterMapList.add(afterMap);
            }

            log.info("buildRowData->{}", afterMapList.toString());
        }



      /*  log.info("TemplateHolder.posMap->{}",TemplateHolder.posMap.toString());
       // TemplateHolder.posMap

        List<Map<String, String>> afterMapList = new ArrayList<>();

        for (Serializable[] after : getAfterValues(eventData)) {

            Map<String, String> afterMap = new HashMap<>();

            int colLen = after.length;

            for (int ix = 0; ix < colLen; ++ix) {
                System.out.println("==================");

                // 取出当前位置对应的列名
                String colName = TemplateHolder.posMap.get(ix);
                log.info("colName---->{}",colName);
                // 如果没有则说明不关心这个列
                if (null == colName) {
                    log.debug("ignore position: {}", ix);
                    continue;
                }

                String colValue = after[ix].toString();
                afterMap.put(colName, colValue);
            }

            log.info(afterMap.toString());

            afterMapList.add(afterMap);
        }

        BinLogKafkaData rowData = new BinLogKafkaData();
        rowData.setAfter(afterMapList);
        rowData.setTableName(tableName);

        return rowData;*/
      return null;
   }
}
