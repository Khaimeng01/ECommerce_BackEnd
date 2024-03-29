// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: To allow Front End to communicate with Back end  order services

package com.example.ECommerce_BackEnd.controller;

import com.example.ECommerce_BackEnd.model.additonalClasses.orderBuyerDetails;
import com.example.ECommerce_BackEnd.model.additonalClasses.order_CustomerPastOrders;
import com.example.ECommerce_BackEnd.model.additonalClasses.order_SellerOrders;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.orderDetails;
import com.example.ECommerce_BackEnd.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/order")
public class orderController {

    @Autowired
    private final orderService orderService;



    public orderController(com.example.ECommerce_BackEnd.service.orderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/getBuyer")
    public orderBuyerDetails findBuyerOrderDetails(@RequestParam(value = "customer_username") String customerUsername){
        return orderService.findBuyerOrderDetails(customerUsername);
    }

    @GetMapping("/getSeller")
    public orderBuyerDetails findSellerDetails(@RequestParam(value = "seller_username") String customerUsername){
        return orderService.findBuyerOrderDetails(customerUsername);
    }


    @PostMapping("/post")
    public ResponseEntity<orderDetails> saveOrder(@RequestBody orderDetails orderDetails){
        return orderService.saveOrder(orderDetails);
    }


    @GetMapping("/get/orderCustomerPastOrders")
    public ResponseEntity<List<order_CustomerPastOrders>> findBuyerOrderDetails2(@RequestParam(value = "customer_username") String customerUsername){
        return orderService.findBuyerOrderDetails2(customerUsername);
    }

    @GetMapping("/get/orderSellerOrders")
    public ResponseEntity<List<order_SellerOrders>> findSellerOrderDetails(@RequestParam(value = "seller_username")String sellerUsername){
        return orderService.findSellerOrderDetails(sellerUsername);
    }


}
