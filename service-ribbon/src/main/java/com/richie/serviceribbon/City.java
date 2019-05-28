package com.richie.serviceribbon;

import lombok.Data;

import java.io.Serializable;

/**
 * 城市实体类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Data
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String desc;

    public City() {
    }

    public City(Long id, Long provinceId, String cityName, String desc) {
        this.id = id;
        this.provinceId = provinceId;
        this.cityName = cityName;
        this.desc = desc;
    }
}
