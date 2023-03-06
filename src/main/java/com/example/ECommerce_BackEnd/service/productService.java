package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface productService {

    public product addNewProduct(product product);

    List<product> getAllProducts();

    List<product> getProduct(long productId);

}
