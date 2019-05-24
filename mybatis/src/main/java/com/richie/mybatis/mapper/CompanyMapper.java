package com.richie.mybatis.mapper;

import com.richie.mybatis.entity.Company;

public interface CompanyMapper {
    int insert(Company record);

    int insertSelective(Company record);
}