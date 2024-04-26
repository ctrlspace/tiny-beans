package com.tinybeans.backend.evaluation.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.tinybeans.backend.evaluation.data.entity.Orders;
import com.tinybeans.backend.evaluation.data.payment.Cart;
import com.tinybeans.backend.evaluation.data.payment.CreditCard;
import com.tinybeans.backend.evaluation.data.payment.OrderResponse;
import com.tinybeans.backend.evaluation.repo.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService{
    private static final String STRIPE_API_KEY = "sk_test_pUZfUOO6SYrX3m1dTQQ8iV0A";

    private final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private final OrderRepository repo;

    public PaymentService(OrderRepository repo){
        this.repo = repo;
    }

    //Using charge object since it's easier based on the requirements
    @Transactional
    public OrderResponse purchase(Cart cart) {
        log.warn("Purchasing products");
        Stripe.apiKey = STRIPE_API_KEY;

        final CreditCard cc = cart.getCc();
        com.tinybeans.backend.evaluation.data.payment.Customer cartCustomer = cart.getCustomer();
        final String customerEmail = cartCustomer.getEmail();

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> customerParams = new HashMap<String, Object>();

        cardParams.put("number", cc.getNumber());
        cardParams.put("exp_month", cc.getExpiry().substring(0,2));
        cardParams.put("exp_year", cc.getExpiry().substring(2));
        cardParams.put("cvc", cc.getCvc());
        cardParams.put("name", cc.getName());
        cardParams.put("address_zip", cartCustomer.getAddress().getZip());

        tokenParams.put("card", cardParams);


        customerParams.put("email", customerEmail);
        customerParams.put("description", cartCustomer.getName());

        chargeParams.put("amount", cart.totalCostCents());
        chargeParams.put("statement_descriptor", "TEST PAYMENT");
        chargeParams.put("receipt_email", customerEmail);
        chargeParams.put("currency", "usd");
        chargeParams.put("capture", true);

        return processTransaction(cart, customerParams, tokenParams, chargeParams);
    }

    public OrderResponse processTransaction(Cart cart, Map<String, Object> customerParams, Map<String, Object> tokenParams, Map<String, Object> chargeParams) {
        try {
            Token token = Token.create(tokenParams);
            final String tokenId = token.getId();
            customerParams.put("source", tokenId);

            Customer customer = Customer.create(customerParams);
            chargeParams.put("customer", customer.getId());

            Charge charge = Charge.create(chargeParams);
            if(null != charge.getId()) {
                Orders order = new Orders(cart.getItems(), cart.totalCost(), charge.getId());
                repo.saveAndFlush(order);
                OrderResponse orderResponse = new OrderResponse(order);
                log.warn("Order response: {}", orderResponse);
                return orderResponse;
            }
        } catch( StripeException e) {
            OrderResponse orderResponse = new OrderResponse(false, e.getCode(), e.getMessage());
            orderResponse.setStripeError(true);
            log.warn("ERROR Purchasing products: {}",e.getMessage());
            return orderResponse;
        }

        OrderResponse orderResponse = new OrderResponse(false, "-99", "Unknown Error");
        log.warn("Order response: {}", orderResponse);

        return orderResponse;
    }
}