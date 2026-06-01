package ru.bgpu.autumn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.bgpu.autumn.dto.UserCreateDTO;
import ru.bgpu.autumn.dto.UserDTO;
import ru.bgpu.autumn.exceptions.ResourceNotFoundException;
import ru.bgpu.autumn.models.User;
import ru.bgpu.autumn.services.GroupService;
import ru.bgpu.autumn.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired private UserService userService;
    @Autowired private GroupService groupService;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping
    List<UserDTO> index() {
        return userService
                .listUsers()
                .stream()
                .map(u -> u.toDto())
                .collect(Collectors.toList());
    }

    @GetMapping("/current-user")
    UserDTO currentUser(Authentication authentication) {
        return userService
                .getByLogin(authentication.getName())
                .map(User::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    UserDTO create(@RequestBody UserCreateDTO dto) {
        User user = new User(dto.getName(), dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getGroups() != null) {
            for (String groupName : dto.getGroups()) {
                groupService.getByName(groupName).ifPresent(g -> user.getGroups().add(g));
            }
        }
        return userService.save(user).toDto();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    UserDTO update(@PathVariable Long id, @RequestBody UserCreateDTO dto) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        user.getGroups().clear();
        if (dto.getGroups() != null) {
            for (String groupName : dto.getGroups()) {
                groupService.getByName(groupName).ifPresent(g -> user.getGroups().add(g));
            }
        }
        return userService.save(user).toDto();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
