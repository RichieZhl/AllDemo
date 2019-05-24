package com.richie.esdemo.esentiry;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * demo
 * <p>
 * Created by lylaut on 2019-05-23
 */
@Data
@ToString
@NoArgsConstructor
public class EsModel {
    private String id;
    private String name;
    private int age;
    private Date date;
}
