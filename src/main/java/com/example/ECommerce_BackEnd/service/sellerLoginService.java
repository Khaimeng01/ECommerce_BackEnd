package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.additonalClasses.sellerData2;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface sellerLoginService {

    //1. Current login
    String authenticateSellerLogin( String customerUsername,String customerPassword);

    //2. Register Seller
    ResponseEntity<sellerLogin> saveSellerLogin(sellerData2 sellerData2) throws Exception;

    //3. For Profile Management
    ResponseEntity<List<sellerLogin>> findSellerPersonalInformation(String sellerUsername) throws Exception;

    //4. Update Seller Data [Profile Management]
    ResponseEntity<String> updateSellerData(sellerLogin sellerLogin, String sellerUsername);
}
