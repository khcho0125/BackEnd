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
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
//@MapperScan(value = "com.doubledb.mapper.OneData") @Mapper 방법 사용시
@PropertySource(value = "classpath:/application.properties")
public class OneDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.hikari.one-data")
    public DataSource OneDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public SqlSessionFactory OneDataSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(OneDataSource());

        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:WEB-INF/OneDataMapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);

        sqlSessionFactoryBean.setTypeAliasesPackage("com.doubledb.*");
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);

        return sqlSessionFactory;
    }

    @Bean
    @Primary
    public SqlSession OneDataSqlSession() throws Exception {
        return new SqlSessionTemplate(OneDataSqlSessionFactoryBean());
    }

    @Bean
    @Primary
    public PlatformTransactionManager OneDataTransactionManager() throws Exception {
        return new DataSourceTransactionManager(OneDataSource());
    }

}
