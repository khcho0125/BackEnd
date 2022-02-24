package com.doubledb.mapper.OneData;

import com.doubledb.model.SalaryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OneDataMapper {

    @Select("select `one-data`.salary.ID, NAME, EMAIL from salary WHERE id = #{value}")
    SalaryModel selectSalary(Long value);
}
