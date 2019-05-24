package com.richie.mybatis.controller;

import com.richie.mybatis.entity.User;
import com.richie.mybatis.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * demo
 * <p>
 * Created by lylaut on 2019-05-23
 */
@RestController
@RequestMapping("/api/")
public class MainController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "getUser/{id}", method = RequestMethod.GET)
    public User getUserWithId(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "getAllUser", method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Object inserUserWith(@RequestBody User user) {
        return userService.addUser(user);
    }
}
