package com.tinybeans.backend.evaluation.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author danrodrigues
 * Date: 13/5/22
 */
@Entity @Data @ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Item extends BaseEntity{

    // Removed this since we are persisting a "Product" entity and it includes this info
    // private String name, description, photo_url;
    /**
     * Added quantity
     */
    private Integer quantity;
    /**
     * Product entity object may change its price so persist the price at time of order.
     */
    private Double price;

    /**
     * @ManyToOne(cascade = CascadeType.MERGE)
     * private Orders orders;
     *
     * Change this from @ManyToMany to @OneToMany since an order item is exclusive to an order
     * and we introduced a "Product" entity.
     *
     */
    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public Long priceAsCents() {
        return Math.round(100*this.price);
    }
}
