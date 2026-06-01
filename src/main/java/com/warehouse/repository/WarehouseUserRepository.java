package com.warehouse.repository;

import com.warehouse.model.WarehouseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseUserRepository extends JpaRepository<WarehouseUser, Long> {
}
