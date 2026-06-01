package com.warehouse.repository;

import com.warehouse.model.RepairCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairCategoryRepository extends JpaRepository<RepairCategory, Long> {
}
