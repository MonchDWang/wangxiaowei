package cn.itcast.service;

import cn.itcast.LogConfig;
import cn.itcast.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",fallback=UserClientImpl.class,configuration = LogConfig.class)
public interface UserClient {
    @GetMapping("/user/{id}")   //此方法一定要匹配服务提供者的方法路径和返回类型
    User findById(@PathVariable("id") Long id);

}
