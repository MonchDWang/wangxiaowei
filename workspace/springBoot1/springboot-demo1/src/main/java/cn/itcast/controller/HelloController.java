package cn.itcast.controller;

import cn.itcast.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
@Controller
@ResponseBody
*/
@RestController
@Slf4j

public class HelloController {

    @Autowired
    private User user;

//    @RequestMapping(value = "/hello" ,method= RequestMethod.GET)
    @GetMapping("/user/hello")
    public String sayHello(){
        return "这是我第一个springboot项目sayHello方法";
    }
    @GetMapping("/hello/hello")
    public String sayHello2(){
        return "这是我第一个springboot项目sayHello2方法";
    }
}
