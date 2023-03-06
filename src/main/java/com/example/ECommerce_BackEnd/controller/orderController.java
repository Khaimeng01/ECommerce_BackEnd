package com.example.ECommerce_BackEnd.controller;

import com.example.ECommerce_BackEnd.model.additonalClasses.orderBuyerDetails;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.orderDetails;
import com.example.ECommerce_BackEnd.service.orderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/order")
public class orderController {

    private final orderService orderService;

    public orderController(com.example.ECommerce_BackEnd.service.orderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get")
    public orderBuyerDetails findBuyerOrderDetails(@RequestParam(value = "customer_username") String customerUsername){
        return orderService.findBuyerOrderDetails(customerUsername);
    }

    @PostMapping("/post")
    public ResponseEntity<orderDetails> saveCustomerLogin(@RequestBody orderDetails orderDetails){
        return orderService.saveOrder(orderDetails);
    }


}
