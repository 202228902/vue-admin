package com.galaxy.empvue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableCaching
public class EmpVueApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpVueApplication.class, args);
    }
}