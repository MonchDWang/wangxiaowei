package cn.itcast.service.impl;

import cn.itcast.mapper.UserMapper;
import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findById(Long id) {
//        return userMapper.selectByPrimaryKey(id); //通用mapper的
        return userMapper.findById(id).get(); //springDataJPA 的
    }
}
