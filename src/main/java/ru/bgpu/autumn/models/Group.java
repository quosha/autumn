package ru.bgpu.autumn.models;

import jakarta.persistence.*;
import ru.bgpu.autumn.util.AutumnDescription;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @AutumnDescription("Администратор системы")
    public static final String GROUP_ADMIN = "ADMIN";
    @AutumnDescription("Модератор контента")
    public static final String GROUP_MANAGER = "MANAGER";
    @AutumnDescription("Пользователь системы")
    public static final String GROUP_USER = "USER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "groups",fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
