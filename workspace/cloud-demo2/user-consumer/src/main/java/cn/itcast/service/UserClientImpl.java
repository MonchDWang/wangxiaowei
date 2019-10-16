package cn.itcast.service;

import cn.itcast.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setUserName("用户不存在");
        return user;
    }
}
