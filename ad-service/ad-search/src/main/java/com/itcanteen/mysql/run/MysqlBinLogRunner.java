package com.itcanteen.mysql.run;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/9 17:18
 */
@Component
public class MysqlBinLogRunner  implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.print("=======================");

        BinaryLogClient client =
                new BinaryLogClient("127.0.0.1",
                        3306,
                        "root",
                        "root");

        client.registerEventListener(event -> {
            EventData data = event.getData();
            //修改
            if(data instanceof UpdateRowsEventData){
                System.out.println(data.toString());
                //添加
            }else if(data instanceof WriteRowsEventData){
                System.out.println(data.toString());
            }else if(data instanceof DeleteRowsEventData){
                System.out.println(data.toString());
            }
        });

        client.connect();
    }
}
