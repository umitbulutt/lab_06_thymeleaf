package com.cydeo.repository;


import com.cydeo.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    boolean save(Product product);

    Product findProductById(UUID productId);

    List<Product> findAll();
}
