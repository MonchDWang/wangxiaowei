package cn.itcast.controller;

import cn.itcast.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController  //方法的所有请求都json返回
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

   /* 
   @RequestMapping("/{id}")
    public User findById(@PathVariable("id") Long id){  //PathVariable从路径上获取参数
        User user = restTemplate.getForObject("http://localhost:8081/user/" + id, User.class);
        return user;
    }
    */

   /*@Autowired
   private DiscoveryClient discoveryClient;
    @RequestMapping("/{id}")
    public User findById(@PathVariable("id") Long id){  //PathVariable从路径上获取参数
//        从eureka中获取服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");//user-service:服务id，就是注册到eureka中的项目名
//        获取第一个服务实例
        ServiceInstance serviceInstance = instances.get(0);
//        获取实例的url地址
        String  url = serviceInstance.getUri().toString();
        System.out.println("url:"+url);//http://localhost:8081
        User user = restTemplate.getForObject(url+"/user/" + id, User.class);
        return user;
    }*/


    @RequestMapping("/{id}")
    public User findById(@PathVariable("id") Long id){  //PathVariable从路径上获取参数
        User user = restTemplate.getForObject("http://user-service/user/" + id, User.class);
        return user;
    }
}
