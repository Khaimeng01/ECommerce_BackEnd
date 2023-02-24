package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface productService {

    public product addNewProduct(product product);

    List<product> getAllProducts();

}
