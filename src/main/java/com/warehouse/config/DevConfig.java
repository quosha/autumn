package com.warehouse.config;

import com.warehouse.model.*;
import com.warehouse.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    CommandLineRunner initDatabase(
            RepairCategoryRepository repairCategoryRepository,
            ClientRepository clientRepository,
            WarehouseUserRepository warehouseUserRepository,
            WarehouseGroupRepository warehouseGroupRepository,
            ServiceRequestRepository serviceRequestRepository) {

        return args -> {
            WarehouseGroup storekeeper = warehouseGroupRepository.save(new WarehouseGroup("STOREKEEPER", "Обычный мастер"));
            WarehouseGroup admin = warehouseGroupRepository.save(new WarehouseGroup("ADMIN", "Администратор сервисного центра"));

            WarehouseUser ivanov = warehouseUserRepository.save(new WarehouseUser("ivanov", "pass1", "ivanov@service.ru"));
            WarehouseUser petrov = warehouseUserRepository.save(new WarehouseUser("petrov", "pass2", "petrov@service.ru"));
            WarehouseUser sidorov = warehouseUserRepository.save(new WarehouseUser("sidorov", "pass3", "sidorov@service.ru"));

            ivanov.getGroups().add(storekeeper);
            petrov.getGroups().add(storekeeper);
            sidorov.getGroups().add(admin);
            warehouseUserRepository.save(ivanov);
            warehouseUserRepository.save(petrov);
            warehouseUserRepository.save(sidorov);

            RepairCategory laptop = repairCategoryRepository.save(new RepairCategory("Ноутбук"));
            RepairCategory tv = repairCategoryRepository.save(new RepairCategory("Телевизор"));
            RepairCategory systemBlock = repairCategoryRepository.save(new RepairCategory("Системный блок"));
            RepairCategory smartphone = repairCategoryRepository.save(new RepairCategory("Смартфон"));
            RepairCategory printer = repairCategoryRepository.save(new RepairCategory("Принтер"));

            Client smith = clientRepository.save(new Client("John", "Smith", "+1-555-0101", "john@mail.com"));
            Client doe = clientRepository.save(new Client("Jane", "Doe", "+1-555-0102", "jane@mail.com"));
            Client brown = clientRepository.save(new Client("Bob", "Brown", "+1-555-0103", "bob@mail.com"));

            ServiceRequest r1 = new ServiceRequest(smith, laptop, RequestStatus.COMPLETED, ivanov);
            r1.setRepairDeadline(LocalDate.of(2026, 5, 10));
            r1.setCost(3000);
            serviceRequestRepository.save(r1);

            ServiceRequest r2 = new ServiceRequest(doe, tv, RequestStatus.IN_WORK, petrov);
            r2.setRepairDeadline(LocalDate.of(2026, 6, 1));
            r2.setCost(5000);
            serviceRequestRepository.save(r2);

            ServiceRequest r3 = new ServiceRequest(brown, systemBlock, RequestStatus.CREATED, ivanov);
            r3.setRepairDeadline(LocalDate.of(2026, 6, 15));
            r3.setCost(2500);
            serviceRequestRepository.save(r3);

            ServiceRequest r4 = new ServiceRequest(smith, smartphone, RequestStatus.READY, petrov);
            r4.setRepairDeadline(LocalDate.of(2026, 5, 25));
            r4.setCost(1500);
            serviceRequestRepository.save(r4);

            ServiceRequest r5 = new ServiceRequest(doe, printer, RequestStatus.CANCELLED, ivanov);
            r5.setRepairDeadline(LocalDate.of(2026, 5, 5));
            r5.setCost(1000);
            serviceRequestRepository.save(r5);

            System.out.println("=== Данные сервисного центра инициализированы ===");
            System.out.println("Пользователи: ivanov (storekeeper), petrov (storekeeper), sidorov (admin)");
            System.out.println("Группы: STOREKEEPER, ADMIN");
            System.out.println("Категории ремонта: Ноутбук, Телевизор, Системный блок, Смартфон, Принтер");
            System.out.println("Клиенты: John Smith, Jane Doe, Bob Brown");
            System.out.println("Заявок в журнале: " + serviceRequestRepository.count());
        };
    }
}
