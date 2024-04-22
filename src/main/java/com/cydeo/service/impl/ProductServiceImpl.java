package com.cydeo.service.impl;


import com.cydeo.model.Product;
import com.cydeo.repository.ProductRepository;
import com.cydeo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean productCreate(Product product) {
        //we need to save product to database
        product.setId(UUID.randomUUID());
        return productRepository.save(product);
    }

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(UUID uuid) {
        return productRepository.findProductById(uuid);
    }

}