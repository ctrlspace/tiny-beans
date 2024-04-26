package com.tinybeans.backend.evaluation.controller;

import com.tinybeans.backend.evaluation.data.entity.Orders;
import com.tinybeans.backend.evaluation.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController{

    private final OrderService service;

    public OrderController(OrderService service){
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    public List<Orders> orders() {
        return service.orders();
    }
}
