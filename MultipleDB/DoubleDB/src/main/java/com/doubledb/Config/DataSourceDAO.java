package com.doubledb.Config;

import com.doubledb.model.CountryModel;
import com.doubledb.model.SalaryModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DataSourceDAO {

    private static final String NAME_SPACE = "com.doubledb.Config.DataSourceDAO";

    @Autowired
    @Qualifier(value = "OneDataSqlSession")
    private SqlSession OneDataSqlSession;

    @Autowired
    @Qualifier(value = "TwoDataSqlSession")
    private SqlSession TwoDataSqlSession;

    public SalaryModel getSalary(Long value) {
        return OneDataSqlSession.selectOne(NAME_SPACE + ".OneData.selectSalary", value);
    }

    public CountryModel getCountry(Long value) {
        return TwoDataSqlSession.selectOne(NAME_SPACE + ".TwoData.selectCountry", value);
    }
}
