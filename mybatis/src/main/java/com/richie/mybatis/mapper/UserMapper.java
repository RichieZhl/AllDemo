package com.richie.mybatis.mapper;

import com.richie.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int insert(User record);

    User selectByPrimaryKey(Long id);

    @Select("select * from User")
    public List<User> findAll();
}