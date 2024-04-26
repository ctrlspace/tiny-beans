package com.tinybeans.backend.evaluation.repo;

import com.tinybeans.backend.evaluation.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
