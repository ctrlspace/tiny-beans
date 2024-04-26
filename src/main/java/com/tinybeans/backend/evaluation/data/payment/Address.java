package com.tinybeans.backend.evaluation.data.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Address{
    private String address;
    private String city;
    private String state;
    private String zip;
}
