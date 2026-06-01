package com.warehouse.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "repair_deadline")
    private LocalDate repairDeadline;

    private Integer cost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private RepairCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private WarehouseUser master;

    public ServiceRequest() {}

    public ServiceRequest(Client client, RepairCategory category, RequestStatus status, WarehouseUser master) {
        this.client = client;
        this.category = category;
        this.status = status;
        this.master = master;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDate getRepairDeadline() { return repairDeadline; }
    public void setRepairDeadline(LocalDate repairDeadline) { this.repairDeadline = repairDeadline; }
    public Integer getCost() { return cost; }
    public void setCost(Integer cost) { this.cost = cost; }
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
    public RepairCategory getCategory() { return category; }
    public void setCategory(RepairCategory category) { this.category = category; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public WarehouseUser getMaster() { return master; }
    public void setMaster(WarehouseUser master) { this.master = master; }
}
