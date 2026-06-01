package ru.bgpu.autumn.services;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String message(String name) {
        return "Hello, "+name+"!";
    }

}
