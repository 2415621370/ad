package com.itcanteen.mysql.run;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.itcanteen.mysql.listenter.MySqlBinLogListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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





        client.connect();
    }
}
