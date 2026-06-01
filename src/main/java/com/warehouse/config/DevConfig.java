package com.warehouse.config;

import com.warehouse.model.*;
import com.warehouse.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    CommandLineRunner initDatabase(
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            WarehouseUserRepository warehouseUserRepository,
            WarehouseGroupRepository warehouseGroupRepository,
            MovementJournalRepository movementJournalRepository) {

        return args -> {
            WarehouseGroup storekeeper = warehouseGroupRepository.save(new WarehouseGroup("STOREKEEPER", "Обычный кладовщик"));
            WarehouseGroup admin = warehouseGroupRepository.save(new WarehouseGroup("ADMIN", "Администратор склада"));

            WarehouseUser ivanov = warehouseUserRepository.save(new WarehouseUser("ivanov", "pass1", "ivanov@warehouse.ru"));
            WarehouseUser petrov = warehouseUserRepository.save(new WarehouseUser("petrov", "pass2", "petrov@warehouse.ru"));
            WarehouseUser sidorov = warehouseUserRepository.save(new WarehouseUser("sidorov", "pass3", "sidorov@warehouse.ru"));

            ivanov.getGroups().add(storekeeper);
            petrov.getGroups().add(storekeeper);
            sidorov.getGroups().add(admin);
            warehouseUserRepository.save(ivanov);
            warehouseUserRepository.save(petrov);
            warehouseUserRepository.save(sidorov);

            Category electronics = categoryRepository.save(new Category("Электроника"));
            Category furniture = categoryRepository.save(new Category("Мебель"));
            Category stationery = categoryRepository.save(new Category("Канцелярия"));
            Category clothing = categoryRepository.save(new Category("Одежда"));
            Category food = categoryRepository.save(new Category("Продукты"));

            Product laptop = productRepository.save(new Product("Ноутбук", new BigDecimal("85000"), electronics));
            Product desk = productRepository.save(new Product("Письменный стол", new BigDecimal("12000"), furniture));
            Product paper = productRepository.save(new Product("Бумага A4", new BigDecimal("500"), stationery));
            Product jacket = productRepository.save(new Product("Куртка", new BigDecimal("4500"), clothing));
            Product coffee = productRepository.save(new Product("Кофе", new BigDecimal("800"), food));

            laptop.setAvailableCopies(10);
            desk.setAvailableCopies(5);
            paper.setAvailableCopies(100);
            jacket.setAvailableCopies(20);
            coffee.setAvailableCopies(50);
            productRepository.save(laptop);
            productRepository.save(desk);
            productRepository.save(paper);
            productRepository.save(jacket);
            productRepository.save(coffee);

            movementJournalRepository.save(new MovementJournal(laptop, MovementType.INCOME, 10, sidorov));
            movementJournalRepository.save(new MovementJournal(desk, MovementType.INCOME, 5, sidorov));
            movementJournalRepository.save(new MovementJournal(paper, MovementType.INCOME, 100, ivanov));
            movementJournalRepository.save(new MovementJournal(jacket, MovementType.INCOME, 20, petrov));
            movementJournalRepository.save(new MovementJournal(coffee, MovementType.INCOME, 50, ivanov));

            movementJournalRepository.save(new MovementJournal(laptop, MovementType.OUTCOME, 2, petrov));
            movementJournalRepository.save(new MovementJournal(paper, MovementType.OUTCOME, 10, ivanov));
            movementJournalRepository.save(new MovementJournal(jacket, MovementType.OUTCOME, 5, petrov));

            System.out.println("=== Данные инициализированы ===");
            System.out.println("Пользователи: ivanov (storekeeper), petrov (storekeeper), sidorov (admin)");
            System.out.println("Группы: STOREKEEPER, ADMIN");
            System.out.println("Категории: Электроника, Мебель, Канцелярия, Одежда, Продукты");
            System.out.println("Товары: Ноутбук (10), Стол (5), Бумага A4 (100), Куртка (20), Кофе (50)");
            System.out.println("Проводок в журнале: " + movementJournalRepository.count());
        };
    }
}
