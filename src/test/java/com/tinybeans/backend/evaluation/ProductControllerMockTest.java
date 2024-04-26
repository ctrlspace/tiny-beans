package com.tinybeans.backend.evaluation;

import com.tinybeans.backend.evaluation.controller.OrderController;
import com.tinybeans.backend.evaluation.controller.ProductController;
import com.tinybeans.backend.evaluation.service.OrderService;
import com.tinybeans.backend.evaluation.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ProductController.class, OrderController.class})
public class ProductControllerMockTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;
    @MockBean
    private OrderService orderService;

    @Test
    void productControllerTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/products")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void orderControllerTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/orders")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("[")));
    }
}
