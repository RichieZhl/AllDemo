package com.richie.pstsql.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richie.pstsql.entity.User;
import com.richie.pstsql.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * pstsql
 * <p>
 * Created by lylaut on 2019-05-17
 */
@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/all")
    public List<User> getAllUsers() throws JsonProcessingException {

        return userRepository.findAll();
    }
}
