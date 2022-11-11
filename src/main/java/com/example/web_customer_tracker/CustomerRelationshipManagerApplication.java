package com.example.web_customer_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ImportResource("classpath:spring-mvc-demo.xml")
public class CustomerRelationshipManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerRelationshipManagerApplication.class, args);
	}
}
