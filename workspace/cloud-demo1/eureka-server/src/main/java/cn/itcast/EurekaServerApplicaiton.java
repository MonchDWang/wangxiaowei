package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //表示本项目是一个注册中心
public class EurekaServerApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplicaiton.class,args);
    }
}
