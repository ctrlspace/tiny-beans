package com.tinybeans.backend.evaluation.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author danrodrigues
 * Date: 13/5/22
 */
@Entity @Data @ToString(exclude={"paymentId"})
@EqualsAndHashCode(callSuper = true)
public class Orders extends BaseEntity {

    /**
     * @ManyToMany
     * Change this from @ManyToMany to @OneToMany since an order item is exclusive to an order
     * and we introduced a "Product" entity.
    **/
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    private List<Item> items;

    @Column(scale = 2, precision = 5)
    private BigDecimal subTotal, discount, finalPrice;

    private String paymentId;

    public Orders(List<Item> items, Double finalPrice, String paymentId){
        this.items = items;
        this.finalPrice = BigDecimal.valueOf(finalPrice);
        this.paymentId = paymentId;
    }

    public Orders(){}
}
