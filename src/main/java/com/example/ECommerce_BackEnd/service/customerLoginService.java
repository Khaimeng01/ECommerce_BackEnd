package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.customerLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface customerLoginService {
    List<customerLogin> findAllUsers();
//    ResponseEntity<customerLogin> findById( Integer  customerId);

    String findById2( String customerUsername,String customerPassword);

    ResponseEntity<customerLogin> saveCustomerLogin(customerLogin customerLogin);
}
