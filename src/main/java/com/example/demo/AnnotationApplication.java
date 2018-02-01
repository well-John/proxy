package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AnnotationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(AnnotationApplication.class, args);
        String[] names = app.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
