package com.tinybeans.backend.evaluation.service;

import com.tinybeans.backend.evaluation.data.entity.Orders;
import com.tinybeans.backend.evaluation.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService{

    private final OrderRepository repo;

    public OrderService(OrderRepository repo){
        this.repo = repo;
    }

    public List<Orders> orders() {
        return repo.findAll();
    }
}
