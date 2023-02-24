package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import org.springframework.http.ResponseEntity;

public interface sellerLoginService {

    String findById( String customerUsername,String customerPassword);

    ResponseEntity<sellerLogin> saveSellerLogin(sellerLogin sellerLogin);
}
