package com.mifan.quiz;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author zyw
 * @version 1.0
 * @since 2017年12月12日
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 问卷数据源
     */
    @Configuration
    protected static class quizDataSource {

        private static final String PREFIX = "quiz.datasource";
        
        @Primary
        @Bean
        @ConfigurationProperties(PREFIX)
        public DataSourceProperties quizSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean
        @ConfigurationProperties(PREFIX)
        public DataSource quizzDataSource() {
            return quizSourceProperties().initializeDataSourceBuilder().build();
        }

        @Bean
        public SqlSessionFactory quizSqlSessionFactory() throws Exception {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(quizzDataSource());
            sessionFactory.setConfigLocation(new ClassPathResource("com/mifan/quiz/mybatis-config.xml"));
            return sessionFactory.getObject();
        }

        @Bean
        public DataSourceTransactionManager quizTransactionManager() {
            return new DataSourceTransactionManager(quizzDataSource());
        }

        @Bean
        public SqlSessionTemplate quizSqlSessionTemplate() throws Exception {
            return new SqlSessionTemplate(quizSqlSessionFactory());
        }

        @Bean
        public SqlSessionTemplate quizBatchSqlSessionTemplate() throws Exception {
            return new SqlSessionTemplate(quizSqlSessionFactory(), ExecutorType.BATCH);
        }

    }
}