package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public interface productService {


    public product addNewProduct(product product);


    List<product> getAllProducts();


    List<product> getProduct(long productId);


    List<product> getProductFromSeller(String productOwner);


    ResponseEntity<String> updateProductData(product product, Long productId);


    ResponseEntity<String> deleteProduct(Long productId);


    List<product> getProductFromCategory(String product_category,String product_priceSortingType);


}
