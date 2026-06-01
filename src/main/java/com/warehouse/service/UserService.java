package com.warehouse.service;

import com.warehouse.model.User;
import com.warehouse.model.Group;
import com.warehouse.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean isMemberOfGroup(User user, String groupName) {
        return user.getGroups().stream()
                .anyMatch(g -> g.getName().equals(groupName));
    }

    public Set<String> getGroupNames(User user) {
        return user.getGroups().stream()
                .map(Group::getName)
                .collect(Collectors.toSet());
    }
}
