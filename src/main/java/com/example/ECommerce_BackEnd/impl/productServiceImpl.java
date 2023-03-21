package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class productServiceImpl implements productService {

    private com.example.ECommerce_BackEnd.repository.productRepository productRepository;

    @Autowired
    public productServiceImpl(com.example.ECommerce_BackEnd.repository.productRepository productRepository) {
        this.productRepository = productRepository;
    }

    //    1.Register New Product
    @Override
    public product addNewProduct(product product) {
        return productRepository.save(product);
    }

    //    2.Get All Products
    @Override
    public List<product> getAllProducts() {
        return (List<product>) productRepository.findAll();
    }

    //    3.Get Specific Product
    @Override
    public List<product> getProduct(long productId) {
        Optional<product> p = productRepository.findById(productId);
        return p.map(Collections::singletonList).orElse(Collections.emptyList());
    }

    @Override
    public ResponseEntity<String> deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
        String message = "Success";
        return ResponseEntity.ok().body(message);
    }

    //    4. Get Product for Specific Seller
    @Override
    public List<product> getProductFromSeller(String productOwner) {
        List<product> productsOfSeller = productRepository.getProductFromSeller(productOwner);
        return productsOfSeller;
//        Optional<product> p = (Optional<product>) Optional.ofNullable(productRepository.getProductFromSeller(productOwner));
//        p.ifPresent(productsOfSeller::add);
//        return productsOfSeller.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(productsOfSeller);
    }

}
