package com.mastery.java.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mastery.java.task"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}