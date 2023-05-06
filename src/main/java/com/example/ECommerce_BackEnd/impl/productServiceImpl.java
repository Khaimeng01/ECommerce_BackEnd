// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: To allow system to perform product Service with database using repository


package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import com.example.ECommerce_BackEnd.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class productServiceImpl implements productService {

    private com.example.ECommerce_BackEnd.repository.productRepository productRepository;

    @Autowired
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


    @Override
    public List<product> getProductFromSeller(String productOwner) {
        List<product> productsOfSeller = productRepository.getProductFromSeller(productOwner);
        return productsOfSeller;
    }


    @Override
    public ResponseEntity<String> updateProductData(product product, Long productId) {
        Optional<com.example.ECommerce_BackEnd.model.product> existingProductList = this.productRepository.findById(productId);
        product selectedProduct = existingProductList.get();
        selectedProduct.setProduct_name(product.getProduct_name());
        selectedProduct.setProduct_category(product.getProduct_category());
        selectedProduct.setProduct_quantity(product.getProduct_quantity());
        selectedProduct.setProduct_owner(product.getProduct_owner());
        selectedProduct.setProduct_description(product.getProduct_description());
        selectedProduct.setProductImages(product.getProductImages());
        selectedProduct.setProduct_price(product.getProduct_price());
        this.productRepository.save(selectedProduct);
        String message = "Data save successfully";
        return ResponseEntity.ok().body(message);
    }


    @Override
    public ResponseEntity<String> deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
        String message = "Success";
        return ResponseEntity.ok().body(message);
    }


    @Override

    public List<product> getProductFromCategory(String product_category, String product_priceSortingType) {
        List<product> listOfProducts;
        if (!StringUtils.isEmpty(product_category)) {
            listOfProducts = productRepository.getProductFromCategory(product_category);
        } else {
            listOfProducts = (List<product>) productRepository.findAll();
        }

        if (!StringUtils.isEmpty(product_priceSortingType)) {
            if (product_priceSortingType.equals("ASCENDING")) {
                listOfProducts.sort(Comparator.comparing(product::getProduct_price));
            } else {
                listOfProducts.sort(Comparator.comparing(product::getProduct_price).reversed());
            }
        }

        return listOfProducts;
    }


}
