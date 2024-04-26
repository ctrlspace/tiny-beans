package com.tinybeans.backend.evaluation;

import com.tinybeans.backend.evaluation.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test2")
@Sql(scripts={"classpath:db/product_schema.sql", "classpath:db/import_products.sql"})
public class TestLoadData{

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testLoadDataForTestClass() {
        assertEquals(4, productRepository.findAll().size());
    }
}
