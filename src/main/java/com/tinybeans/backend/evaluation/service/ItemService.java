package com.tinybeans.backend.evaluation.service;

import com.tinybeans.backend.evaluation.data.entity.Item;
import com.tinybeans.backend.evaluation.repo.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService{

    private final ItemRepository repo;

    public ItemService(ItemRepository repo){
        this.repo = repo;
    }

    public List<Item> products() {
        return repo.findAll();
    }
}
