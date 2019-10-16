package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@EnableEurekaClient //声明注册中心使用eureka
@EnableDiscoveryClient //不限制注册中心是eureka还是zookeeper
@MapperScan("cn.itcast.mapper")  //使用的是统一mapper的注解 tk包下的
public class UserServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServicesApplication.class, args);
	}

}
