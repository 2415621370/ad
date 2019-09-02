package com.itcanteen.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * EnableEurekaServer:开始服务注册于发现（服务注册中心）
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/8/31 10:01
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaApplication.class,args);
    }


}
