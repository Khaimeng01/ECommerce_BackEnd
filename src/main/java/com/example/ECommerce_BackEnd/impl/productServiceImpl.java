package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.service.productService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class productServiceImpl implements productService {

    private com.example.ECommerce_BackEnd.repository.productRepository productRepository;

    public productServiceImpl(com.example.ECommerce_BackEnd.repository.productRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public product addNewProduct(product product) {
        return productRepository.save(product);
    }

    @Override
    public List<product> getAllProducts() {
        return (List<product>) productRepository.findAll();
    }

    @Override
    public List<product> getProduct(long productId) {
        Optional<product> p = productRepository.findById(productId);
        return p.map(Collections::singletonList).orElse(Collections.emptyList());
    }
}
