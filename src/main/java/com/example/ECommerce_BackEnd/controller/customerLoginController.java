package com.example.ECommerce_BackEnd.controller;

import com.example.ECommerce_BackEnd.execption.ResourceNotFoundException;
import com.example.ECommerce_BackEnd.model.customerLogin;
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


//    @GetMapping("/get24")
//    public ResponseEntity<customerLogin> findByParam2(@RequestParam(value = "id_customerlogin") Integer customerId){
//        return customerLoginService.findById(customerId);
//    }

    @GetMapping("/get25")
    public String findByPara32(@RequestParam(value = "customer_username") String customerUsername,
                               @RequestParam(value = "customer_password") String customerPassword){
        return customerLoginService.findById2(customerUsername,customerPassword);
    }


    @PostMapping("/post")
    public ResponseEntity<customerLogin> saveCustomerLogin(@RequestBody customerLogin customerLogin){
        return customerLoginService.saveCustomerLogin(customerLogin);
    }



}
