package com.KyLin.SpringBoot_World;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.KyLin.SpringBoot_World.mapper")
public class SpringBootWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWorldApplication.class, args);
	}

}
