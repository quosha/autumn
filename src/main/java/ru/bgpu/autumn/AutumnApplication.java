package ru.bgpu.autumn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"ru.bgpu.autumn", "com.warehouse"})
@EnableJpaRepositories(basePackages = {"ru.bgpu.autumn.repositories", "com.warehouse.repository"})
@EntityScan(basePackages = {"ru.bgpu.autumn.models", "com.warehouse.model"})
public class AutumnApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutumnApplication.class, args);
    }

}
