package com.doubledb.Config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
//@MapperScan(value = "com.doubledb.mapper.TwoData") @Mapper 방법 사용시
@PropertySource(value = "classpath:/application.properties")
public class TwoDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari.two-data")
    public DataSource TwoDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public SqlSessionFactory TwoDataSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(TwoDataSource());

        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:WEB-INF/TwoDataMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);

        sqlSessionFactoryBean.setTypeAliasesPackage("com.doubledb.*");

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);

        return sqlSessionFactory;
    }

    @Bean
    public SqlSession TwoDataSqlSession() throws Exception {
        return new SqlSessionTemplate(TwoDataSqlSessionFactoryBean());
    }

    @Bean
    public PlatformTransactionManager TwoDataTransactionManager() throws Exception {
        return new DataSourceTransactionManager(TwoDataSource());
    }
}
