// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: To allow Front End to communicate with Back end customer services
package com.example.ECommerce_BackEnd.controller;

import com.example.ECommerce_BackEnd.execption.ResourceNotFoundException;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ECommerce_BackEnd.repository.customerLoginRepository;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/cs/dataman")
public class customerLoginController {
    private final com.example.ECommerce_BackEnd.service.customerLoginService customerLoginService;
    private final customerLoginRepository customerLoginRepository;


    public customerLoginController(com.example.ECommerce_BackEnd.service.customerLoginService customerLoginService, com.example.ECommerce_BackEnd.repository.customerLoginRepository customerLoginRepository) {
        this.customerLoginService = customerLoginService;
        this.customerLoginRepository = customerLoginRepository;
    }



    @GetMapping("/get")
    public List<customerLogin> findAllUsers(){
        return customerLoginService.findAllUsers();
    }

    @GetMapping("/test")
    public String tester(){
        return "Test_1";
    }


    @GetMapping("/get/userAuthentication")
    public String authenticateUserLogin(@RequestParam(value = "customer_username") String customerUsername,
                               @RequestParam(value = "customer_password") String customerPassword){
        return customerLoginService.authenticateUserLogin(customerUsername,customerPassword);
    }


    @GetMapping("/get/FCPI")
    public ResponseEntity<List<customerLogin>> findCustomerPersonalInformation(@RequestParam(value = "customer_username") String customerUsername){
        return customerLoginService.findCustomerPersonalInformation(customerUsername);
    }


    @PostMapping("/post")
    public ResponseEntity<String> saveCustomerLogin(@RequestBody customerLogin customerLogin){
        return customerLoginService.saveCustomerLogin(customerLogin);
    }


    @PutMapping("/put")
    public ResponseEntity<String> updateCustomerData(@RequestBody customerLogin customerLogin,@RequestParam(value = "customer_username") String customerUsername){
        return customerLoginService.updateCustomerData(customerLogin,customerUsername);
    }


    @GetMapping("/findIfAccountExists")
    public ResponseEntity<String> findIfAccountExists(@RequestParam(value = "customer_username") String customerUsername){
        return customerLoginService.findIfAccountExists(customerUsername);
    }

    @GetMapping("/updatePassword")
    public ResponseEntity<String> updateCustomerPassword(@RequestParam(value = "customer_username") String customerUsername,
                                                         @RequestParam(value = "customer_password") String customerPassword){
        return customerLoginService.updateCustomerPassword(customerUsername,customerPassword);
    }




}
