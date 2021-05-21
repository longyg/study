package com.yglong.spring;

import com.yglong.spring.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableCaching
public class MySpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MySpringApplication.class, args);
        AppConfig appConfig = (AppConfig) context.getBean("appConfig");
        System.out.println(appConfig.getName());
    }

}
