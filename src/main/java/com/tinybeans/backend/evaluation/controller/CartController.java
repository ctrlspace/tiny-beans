package com.tinybeans.backend.evaluation.controller;

import com.tinybeans.backend.evaluation.data.payment.Cart;
import com.tinybeans.backend.evaluation.data.payment.OrderResponse;
import com.tinybeans.backend.evaluation.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController{
    private final PaymentService service;

    public CartController(PaymentService service){
        this.service = service;
    }

    @PostMapping("/purchase")
    @ResponseBody
    public ResponseEntity<OrderResponse> purchase(@RequestBody Cart cart) {
        return new ResponseEntity<>(service.purchase(cart), HttpStatus.OK);
    }
}
