package com.richie.pstsql.repository;

import com.richie.pstsql.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * pstsql
 * <p>
 * Created by lylaut on 2019-05-17
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
}
