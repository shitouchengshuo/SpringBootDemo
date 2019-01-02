package com.qiqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BindApplication {

    public static void main(String[] args) {
        SpringApplication.run(BindApplication.class, args);
    }

}

