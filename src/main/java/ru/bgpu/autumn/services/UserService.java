package ru.bgpu.autumn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bgpu.autumn.models.User;
import ru.bgpu.autumn.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public Optional<User> getByLogin(String login) {
        return userRepository.getOneByLogin(login);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> listUsers() {
        List<User> list = new ArrayList<>();
        for(User user: userRepository.findAll()) {
            list.add(user);
        }
        return list;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

