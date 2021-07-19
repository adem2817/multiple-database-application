package com.filemarket.readwritedatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class ReadWriteDatabaseApplication {

	public final static String MODEL_PACKAGE = "com.filemarket.readwritedatabase.model";

	public final static Properties JPA_PROPERTIES = new Properties() {{
		put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
		put("hibernate.hbm2ddl.auto", "update");
		put("hibernate.ddl-auto", "update");
		put("show-sql", "true");
	}};


	public static void main(String[] args) {
		SpringApplication.run(ReadWriteDatabaseApplication.class, args);
	}

}
