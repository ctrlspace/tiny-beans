package com.tinybeans.backend.evaluation.repo;

import com.tinybeans.backend.evaluation.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long>{
}
