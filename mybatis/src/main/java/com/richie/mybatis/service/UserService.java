package com.richie.mybatis.service;

import com.richie.mybatis.entity.User;

import java.util.List;

/**
 * demo
 * <p>
 * Created by lylaut on 2019-05-23
 */
public interface UserService {
    public User getUserById(Long id);

    public boolean addUser(User user);

    public List<User> findAll();
}
