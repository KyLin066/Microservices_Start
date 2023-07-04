package com.KyLin.SpringBoot_User;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.KyLin.SpringBoot_User.mapper")
public class SpringBootUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUserApplication.class, args);
	}

}
