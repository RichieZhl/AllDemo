package com.richie.mybatis.service.impl;

import com.richie.mybatis.entity.User;
import com.richie.mybatis.mapper.UserMapper;
import com.richie.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * demo
 * <p>
 * Created by lylaut on 2019-05-23
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean addUser(User user) {
        try {
            userMapper.insert(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
