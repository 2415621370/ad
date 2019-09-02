package com.itcanteen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/2 16:52
 */
@SpringBootApplication
@EnableEurekaClient
public class SponsorApplication {
    public static void main(String[] args){
        SpringApplication.run(SponsorApplication.class,args);
    }
}
