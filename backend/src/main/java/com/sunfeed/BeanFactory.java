package com.sunfeed;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.jdbc.datasource.*;

import javax.sql.DataSource;

@Configuration
public class BeanFactory {

//    @Bean
//    public DataSource dataSource(DataSourceProperties props) {
//        return DataSourceBuilder.create().
//                driverClassName("org.postgresql.Driver").
//                url("jdbc:postgresql://localhost:5432/SunFeedDB").
//                username("postgres").
//                password("123456").
//                build();
//    }

//    @Bean
//    public DataSourceTransactionManager dsm() {
//        return new DataSourceTransactionManager(dataSource());
//    }
}
