package com.itcanteen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/9 17:13
 */

@SpringBootApplication
@EnableEurekaClient
public class SearchApplication {
    public static void main(String[] args){
        SpringApplication.run(SearchApplication.class,args);
    }
}
