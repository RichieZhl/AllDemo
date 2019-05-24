package com.richie.pstsql.entity;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * pstsql
 * <p>
 * Created by lylaut on 2019-05-17
 */
@Data
@Entity
@Table(name = "puser")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private int age;
}
