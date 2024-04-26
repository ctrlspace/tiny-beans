package com.tinybeans.backend.evaluation.data.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CreditCard{
    private String cvc;

    private String expiry;

    private String name;

    private String number;
}
