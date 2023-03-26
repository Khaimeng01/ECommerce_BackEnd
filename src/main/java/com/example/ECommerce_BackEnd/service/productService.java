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

    //    1.Register New Product
    public product addNewProduct(product product);

    //    2.Get All Products
    List<product> getAllProducts();

    //    3.Get Specific Product
    List<product> getProduct(long productId);

    ResponseEntity<String> deleteProduct(Long productId);

    //    4. Get Product for Specific Seller
    List<product> getProductFromSeller(String productOwner);


    ResponseEntity<String> updateProductData(product product, Long productId);

//    String findByLai(String product_owner);
}
