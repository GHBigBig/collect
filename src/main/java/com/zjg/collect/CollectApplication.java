package com.zjg.collect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.zjg.collect.dao.mapper")
@SpringBootApplication
public class CollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class, args);
    }

}
