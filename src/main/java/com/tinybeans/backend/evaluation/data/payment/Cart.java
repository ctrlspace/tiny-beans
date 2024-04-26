package com.tinybeans.backend.evaluation.data.payment;

import com.tinybeans.backend.evaluation.data.entity.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Cart{
    private List<Item> items;
    private CreditCard cc;
    private Customer customer;

    public Long totalCostCents(){
        return items.stream().mapToLong(Item::priceAsCents).sum();
    }

    public Double totalCost(){
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}
