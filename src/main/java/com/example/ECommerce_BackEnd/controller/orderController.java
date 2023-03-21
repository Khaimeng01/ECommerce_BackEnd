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

    //1. Get customerData in CheckOutPage
    @GetMapping("/getBuyer")
    public orderBuyerDetails findBuyerOrderDetails(@RequestParam(value = "customer_username") String customerUsername){
        return orderService.findBuyerOrderDetails(customerUsername);
    }

    @GetMapping("/getSeller")
    public orderBuyerDetails findSellerDetails(@RequestParam(value = "seller_username") String customerUsername){
        return orderService.findBuyerOrderDetails(customerUsername);
    }

//    3. Save Order
    @PostMapping("/post")
    public ResponseEntity<orderDetails> saveOrder(@RequestBody orderDetails orderDetails){
        return orderService.saveOrder(orderDetails);
    }

//   4. Get Customer Past order HHistory [Buyer]
    @GetMapping("/get/orderCustomerPastOrders")
    public ResponseEntity<List<order_CustomerPastOrders>> findBuyerOrderDetails2(@RequestParam(value = "customer_username") String customerUsername){
        return orderService.findBuyerOrderDetails2(customerUsername);
    }

//   5. Get Seller Past Order History [Seller]
    @GetMapping("/get/orderSellerOrders")
    public ResponseEntity<List<order_SellerOrders>> findSellerOrderDetails(@RequestParam(value = "seller_username")String sellerUsername){
        return orderService.findSellerOrderDetails(sellerUsername);
    }


}
