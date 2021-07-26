package com.filemarket.readwritedatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.filemarket.readwritedatabase.model.Person")
public class ReadWriteDatabaseApplication {

	public static final String MODEL_PACKAGE = "com.filemarket.readwritedatabase.model";

	public static void main(String[] args) {
		SpringApplication.run(ReadWriteDatabaseApplication.class, args);
	}

}
