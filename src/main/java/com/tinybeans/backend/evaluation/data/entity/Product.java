package com.tinybeans.backend.evaluation.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author danrodrigues
 * Date: 13/5/22
 */
@Entity @Data @ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Product extends BaseEntity{

    private String name, description, photo_url;
    private Double price;

    public Long priceAsCents() {
        return Math.round(100*this.price);
    }
}
