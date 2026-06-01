package ru.bgpu.autumn.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private Long id;

    private String name;
    private String login;
    private List<String> groups = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String login) {
        this.id = id;
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

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
