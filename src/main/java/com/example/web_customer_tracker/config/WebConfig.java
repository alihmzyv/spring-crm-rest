package com.example.web_customer_tracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@PropertySource("classpath:data.source.properties")
public class WebConfig implements WebMvcConfigurer {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private Environment env;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    @Bean
    public DataSource securityDataSource() {
        SimpleDriverDataSource dataSource =  DataSourceBuilder.create().type(SimpleDriverDataSource.class)
                .driverClassName(env.getProperty("jdbc.driver"))
                .url(env.getProperty("jdbc.url"))
                .username(env.getProperty("jdbc.username"))
                .password(env.getProperty("jdbc.password"))
                .build();
        logger.info(String.format("Data Source built: Driver=%s, url=%s, username=%s, password=%s",
                dataSource.getDriver().getClass().getName(),
                dataSource.getUrl(),
                dataSource.getUsername(),
                dataSource.getPassword()));
        return dataSource;
    }
}
