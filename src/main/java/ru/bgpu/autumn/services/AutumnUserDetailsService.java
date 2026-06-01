package ru.bgpu.autumn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bgpu.autumn.models.Group;
import ru.bgpu.autumn.models.User;

import java.util.stream.Collectors;

@Service
public class AutumnUserDetailsService implements UserDetailsService {

    @Autowired private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("login: "+username);

        User user = userService.getByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("Пользователь не найден")
        );
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getGroups().stream().collect(Collectors.toList()))
                .build();
    }
}
