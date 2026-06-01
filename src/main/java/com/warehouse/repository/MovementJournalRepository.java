package com.warehouse.repository;

import com.warehouse.model.MovementJournal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementJournalRepository extends JpaRepository<MovementJournal, Long> {
}
