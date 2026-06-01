package com.warehouse.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warehouse_groups")
public class WarehouseGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "groups")
    private Set<WarehouseUser> users = new HashSet<>();

    public WarehouseGroup() {}

    public WarehouseGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Set<WarehouseUser> getUsers() { return users; }
    public void setUsers(Set<WarehouseUser> users) { this.users = users; }
}
