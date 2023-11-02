package com.dwh.gyndowindback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dwh.gyndowindback.mapper")
public class GyndowindBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(GyndowindBackApplication.class, args);
    }

}
