package com.tinybeans.backend.evaluation.repo;

import com.tinybeans.backend.evaluation.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{
}
