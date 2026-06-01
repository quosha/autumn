package ru.bgpu.autumn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.bgpu.autumn", "com.warehouse"})
public class AutumnApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutumnApplication.class, args);
    }

}
