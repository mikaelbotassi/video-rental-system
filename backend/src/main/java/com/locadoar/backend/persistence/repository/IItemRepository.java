package com.locadoar.backend.persistence.repository;

import com.locadoar.backend.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Integer> {
}
