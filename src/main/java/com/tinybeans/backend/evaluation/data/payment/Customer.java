package com.tinybeans.backend.evaluation.data.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Customer{
    private String email;
    private String name;
    private Address address;
}
