package com.sign.signin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sign.signin.mapper")
public class SignInApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignInApplication.class, args);
    }

}
