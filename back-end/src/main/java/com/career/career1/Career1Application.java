package com.career.career1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication														//连接数据库
// @SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})			//不连接数据库
public class Career1Application {

	public static void main(String[] args) {
		SpringApplication.run(Career1Application.class, args);
	}

}
