package com.dev.fshop;

import org.springdoc.core.SpringDocUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@Configuration
public class FshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(FshopApplication.class, args);
    }

}
