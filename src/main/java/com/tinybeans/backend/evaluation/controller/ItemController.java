package com.tinybeans.backend.evaluation.controller;

import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController{

    private final ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    public List<Item> items() {
        return service.products();
    }
}
