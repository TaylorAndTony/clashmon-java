package com.mine.clashmon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mine.clashmon.mapper")
public class ClashmonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClashmonApplication.class, args);
    }

}
