package com.tinybeans.backend.evaluation.data.payment;

import com.tinybeans.backend.evaluation.data.entity.Orders;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class OrderResponse{
    private String orderId;
    private String paymentId;
    private Double cost;
    private boolean success;
    private boolean stripeError;
    private String errorMessage;
    private String code;

    public OrderResponse(boolean success, String code, String errorMessage){
        this.success = success;
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public OrderResponse(Orders order){
        this.success = true;
        this.orderId = order.getId().toString();
        this.paymentId = order.getPaymentId();
        this.cost = order.getFinalPrice().doubleValue();
    }

    public OrderResponse(){
    }
}
