package com.itcanteen.mysql.run;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.itcanteen.mysql.listenter.MySqlBinLogListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.BitSet;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/9 17:18
 */
@Component
public class MysqlBinLogRunner  implements CommandLineRunner {

    @Autowired
    MySqlBinLogListener logListener;
    @Override
    public void run(String... args) throws Exception {
        System.out.print("=======================");

        BinaryLogClient client =
                new BinaryLogClient("127.0.0.1",
                        3306,
                        "root",
                        "root");

        client.registerEventListener(logListener);

       /* client.registerEventListener(event -> {
            EventData data = event.getData();
           // TableMapEventData tableMapEventData = event.getData();

            //修改
            if(data instanceof UpdateRowsEventData){
                System.out.println(data.toString());
                *//*String table = tableMapEventData.getTable();
                System.out.println(table);
                int[] columnMetadata = tableMapEventData.getColumnMetadata();
                System.out.println(columnMetadata);
                BitSet columnNullability = tableMapEventData.getColumnNullability();
                byte[] columnTypes = tableMapEventData.getColumnTypes();
                System.out.println(columnTypes);
                String database = tableMapEventData.getDatabase();
                System.out.println(database);*//*
                //添加
            }else if(data instanceof WriteRowsEventData){
                System.out.println(data.toString());
               *//* String table = tableMapEventData.getTable();
                System.out.println(table);
                int[] columnMetadata = tableMapEventData.getColumnMetadata();
                System.out.println(columnMetadata);
                BitSet columnNullability = tableMapEventData.getColumnNullability();
                byte[] columnTypes = tableMapEventData.getColumnTypes();
                System.out.println(columnTypes);
                String database = tableMapEventData.getDatabase();
                System.out.println(database);*//*
            }else if(data instanceof DeleteRowsEventData){
                System.out.println(data.toString());
              *//*  String table = tableMapEventData.getTable();
                System.out.println(table);
                int[] columnMetadata = tableMapEventData.getColumnMetadata();
                System.out.println(columnMetadata);
                BitSet columnNullability = tableMapEventData.getColumnNullability();
                byte[] columnTypes = tableMapEventData.getColumnTypes();
                System.out.println(columnTypes);
                String database = tableMapEventData.getDatabase();
                System.out.println(database);*//*
            }
        });*/

        client.connect();
    }
}
