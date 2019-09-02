package com.itcanteen.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 9:08
 */

@SpringBootApplication
@EnableEurekaClient
public class Main {

    public static void main(String[] args){
        SpringApplication.run(Main.class,args);
    }
}
