// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: List of sellerLoginService data functions and name which are linked to the sellerLoginServiceImpl


package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.additonalClasses.sellerData2;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface sellerLoginService {


    String authenticateSellerLogin( String customerUsername,String customerPassword);


    ResponseEntity<String> saveSellerLogin(sellerData2 sellerData2) throws Exception;


    ResponseEntity<List<sellerLogin>> findSellerPersonalInformation(String sellerUsername) throws Exception;


    ResponseEntity<String> updateSellerData(sellerLogin sellerLogin, String sellerUsername);

    ResponseEntity<String> findIfAccountExists(String sellerUsername);

    ResponseEntity<String> updateSellerPassword(String sellerUsername, String sellerPassword);
}
