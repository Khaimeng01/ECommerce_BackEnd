// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: List of customerLoginService data functions and name which are linked to the customerLoginServiceImpl


package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.customerLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface customerLoginService {


    List<customerLogin> findAllUsers();


    String authenticateUserLogin( String customerUsername,String customerPassword);


    ResponseEntity<List<customerLogin>>findCustomerPersonalInformation(String customerUsername);


    ResponseEntity<String> saveCustomerLogin(customerLogin customerLogin);


    ResponseEntity<String> updateCustomerData(customerLogin customerLogin, String customerUsername);

    ResponseEntity<String> findIfAccountExists(String customerUsername);

    ResponseEntity<String> updateCustomerPassword(String customerUsername, String customerPassword);
}
