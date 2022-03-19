package com.example.autopartsmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoPartsMallApplication {

    public static final String PACKAGE_NAME = "com.example.autopartsmall";

    public static void main(String[] args) {
        if (!PACKAGE_NAME.equals(AutoPartsMallApplication.class.getPackage().getName())) {
            throw new RuntimeException("请检查包配置");
        }
        SpringApplication.run(AutoPartsMallApplication.class, args);
    }

}
