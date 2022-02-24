package com.doubledb.mapper.TwoData;

import com.doubledb.model.CountryModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper()
public interface TwoDataMapper {

    @Select("select `two-data`.country.ID, CONTINENT, COUNTRY from country WHERE id = #{value}")
    /*
        테이블이 `two-data`로 지정이 안되는 오류 생김
        폴더를 나누어 MapperScan 해보았지만
        그럼에도 `one-data.country`로 처리됨
    */
    CountryModel selectCountry(Long value);
}
