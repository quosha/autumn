package ru.bgpu.autumn.models;

import jakarta.persistence.*;
import ru.bgpu.autumn.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String login;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Message> messages;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Room> rooms = new ArrayList();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Group> groups = new ArrayList();

    public User() {
    }

    public User(String name, String login) {
        this.name = name;
        this.login = login;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserDTO toDto() {
        UserDTO dto =  new UserDTO(id, name, login);
        dto.setGroups(groups.stream().map(Group::getName).toList());
        return dto;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
