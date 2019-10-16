package cn.itcast.controller;

import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//http://localhost:8080/user/1  restful风格
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/{id}")
    public User findById(@PathVariable("id") Long id){
      return   userService.findById(id);
    }
}
