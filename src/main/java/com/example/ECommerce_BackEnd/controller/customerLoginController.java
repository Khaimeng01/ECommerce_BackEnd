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
//    private final repository.customerLoginRepository customerLoginRepository;

//    public customerLoginController(service.customerLoginService customerLoginService) {
//        super();
//        this.customerLoginService = customerLoginService;
//    }

    public customerLoginController(com.example.ECommerce_BackEnd.service.customerLoginService customerLoginService, com.example.ECommerce_BackEnd.repository.customerLoginRepository customerLoginRepository) {
        this.customerLoginService = customerLoginService;
        this.customerLoginRepository = customerLoginRepository;
    }

    @GetMapping("/get")
    public List<customerLogin> findAllUsers(){
        return customerLoginService.findAllUsers();
    }


    //2. Current login
    @GetMapping("/get/userAuthentication")
    public String authenticateUserLogin(@RequestParam(value = "customer_username") String customerUsername,
                               @RequestParam(value = "customer_password") String customerPassword){
        return customerLoginService.authenticateUserLogin(customerUsername,customerPassword);
    }


    //3. For Profile Management
    @GetMapping("/get/FCPI")
    public ResponseEntity<List<customerLogin>> findCustomerPersonalInformation(@RequestParam(value = "customer_username") String customerUsername){
        return customerLoginService.findCustomerPersonalInformation(customerUsername);
    }


    //4. Register Customer
    @PostMapping("/post")
    public ResponseEntity<String> saveCustomerLogin(@RequestBody customerLogin customerLogin){
        return customerLoginService.saveCustomerLogin(customerLogin);
    }


    //5. Update Customer
    @PutMapping("/put")
    public ResponseEntity<String> updateCustomerData(@RequestBody customerLogin customerLogin,@RequestParam(value = "customer_username") String customerUsername){
        return customerLoginService.updateCustomerData(customerLogin,customerUsername);
    }


    @GetMapping("/getSoorya")
    public String findBySoorya(@RequestParam(value = "customer_username") String customerUsername){
        return customerLoginService.findBySoorya(customerUsername);
    }


}
