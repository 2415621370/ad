package com.itcanteen.test;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/9 16:52
 */
public class MySqlTest {
    public static void main(String[] args) throws IOException {
        BinaryLogClient client =
                new BinaryLogClient("127.0.0.1",
                        3306,
                        "root",
                        "root");


        client.registerEventListener(event -> {
            EventData data = event.getData();
            //修改
            if(data instanceof UpdateRowsEventData){
                long tableId = ((UpdateRowsEventData) data).getTableId();
System.out.println(00000000);
                //((UpdateRowsEventData) data).get

                List<Serializable[]> collect = ((UpdateRowsEventData) data).getRows().stream()
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList());

                for (Serializable[] after : collect){

                    Map<String, String> afterMap = new HashMap<>();

                    int colLen = after.length;

                    for (int ix = 0; ix < colLen; ++ix) {

                        String colValue = after[ix].toString();
                        System.out.println(colValue);
                    }


                }

                System.out.println(data.toString());
                //添加
            }else if(data instanceof WriteRowsEventData){
                System.out.println(data.toString());
                List<Serializable[]> rows = ((WriteRowsEventData) data).getRows();
                System.out.println(rows);
                for (Serializable[] after : rows){

                    Map<String, String> afterMap = new HashMap<>();

                    int colLen = after.length;

                    for (int ix = 0; ix < colLen; ++ix) {

                        String colValue = after[ix].toString();
                        System.out.println(colValue);
                    }


                    }


                }else if(data instanceof DeleteRowsEventData){
                System.out.println(data.toString());
            }
        });

        client.connect();

    }
}
