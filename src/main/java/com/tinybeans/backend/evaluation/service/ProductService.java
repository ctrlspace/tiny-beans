package com.tinybeans.backend.evaluation.service;

import com.tinybeans.backend.evaluation.data.entity.Product;
import com.tinybeans.backend.evaluation.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService{

    private final ProductRepository repo;

    public ProductService(ProductRepository repo){
        this.repo = repo;
    }

    public List<Product> products() {
        return repo.findAll();
    }
}
