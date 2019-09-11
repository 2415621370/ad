package com.itcanteen.test;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/11 15:30
 */
public class Test2 {

    static final ThreadLocal<BinLogKafkaData> sThreadLocal = new ThreadLocal<BinLogKafkaData>();

    public  void set(){
        BinLogKafkaData binLogKafkaData = new BinLogKafkaData();
        binLogKafkaData.setEventType("UPDATE");
        binLogKafkaData.setTableName("ad_user");
        sThreadLocal.set(binLogKafkaData);
    }

    public static void main(String[] args){
        BinLogKafkaData binLogKafkaData = sThreadLocal.get();
        System.out.println(binLogKafkaData.toString());
    }
}
