package com.warehouse.service;

import com.warehouse.model.*;
import com.warehouse.repository.MovementJournalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovementJournalService {

    private final MovementJournalRepository movementJournalRepository;
    private final ProductService productService;

    public MovementJournalService(MovementJournalRepository movementJournalRepository,
                                  ProductService productService) {
        this.movementJournalRepository = movementJournalRepository;
        this.productService = productService;
    }

    @Transactional
    public MovementJournal save(MovementJournal record) {
        Product product = record.getProduct();
        if (record.getMovementType() == MovementType.INCOME) {
            product.setAvailableCopies(product.getAvailableCopies() + record.getQuantity());
        } else {
            product.setAvailableCopies(product.getAvailableCopies() - record.getQuantity());
        }
        productService.save(product);
        return movementJournalRepository.save(record);
    }
}
