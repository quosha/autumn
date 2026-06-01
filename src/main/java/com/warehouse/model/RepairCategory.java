package com.warehouse.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "repair_categories")
public class RepairCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ServiceRequest> requests = new ArrayList<>();

    public RepairCategory() {}

    public RepairCategory(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<ServiceRequest> getRequests() { return requests; }
    public void setRequests(List<ServiceRequest> requests) { this.requests = requests; }
}
