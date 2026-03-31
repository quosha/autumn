package ru.bgpu.autumn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bgpu.autumn.dto.UserDTO;
import ru.bgpu.autumn.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired UserService userService;

    @GetMapping
    List<UserDTO> index() {
        return userService
                .listUsers()
                .stream()
                .map(u -> u.toDto())
                .collect(Collectors.toList());
    }
}
