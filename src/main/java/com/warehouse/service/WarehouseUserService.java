package com.warehouse.service;

import com.warehouse.model.WarehouseUser;
import com.warehouse.model.WarehouseGroup;
import com.warehouse.repository.WarehouseUserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WarehouseUserService {

    private final WarehouseUserRepository warehouseUserRepository;

    public WarehouseUserService(WarehouseUserRepository warehouseUserRepository) {
        this.warehouseUserRepository = warehouseUserRepository;
    }

    public WarehouseUser save(WarehouseUser user) {
        return warehouseUserRepository.save(user);
    }

    public boolean isMemberOfGroup(WarehouseUser user, String groupName) {
        return user.getGroups().stream()
                .anyMatch(g -> g.getName().equals(groupName));
    }

    public Set<String> getGroupNames(WarehouseUser user) {
        return user.getGroups().stream()
                .map(WarehouseGroup::getName)
                .collect(Collectors.toSet());
    }
}
