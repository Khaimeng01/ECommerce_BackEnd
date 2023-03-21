package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.customerLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface customerLoginService {

    //Return All Users [DELETE SOON]
    List<customerLogin> findAllUsers();

    //2. Current login
    String authenticateUserLogin( String customerUsername,String customerPassword);

    //3. For Profile Management
    ResponseEntity<List<customerLogin>>findCustomerPersonalInformation(String customerUsername);

    //4. Register Customer
    ResponseEntity<customerLogin> saveCustomerLogin(customerLogin customerLogin);

    //5. Update Customer
    ResponseEntity<String> updateCustomerData(customerLogin customerLogin, String customerUsername);

    String findBySoorya(String customerUsername);
}
