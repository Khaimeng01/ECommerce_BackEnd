package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.repository.productRepository;
import com.example.ECommerce_BackEnd.service.productService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
