package com.tinybeans.backend.evaluation.controller;

import com.tinybeans.backend.evaluation.data.entity.Product;
import com.tinybeans.backend.evaluation.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController{

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    public List<Product> products() {
        return service.products();
    }
}
