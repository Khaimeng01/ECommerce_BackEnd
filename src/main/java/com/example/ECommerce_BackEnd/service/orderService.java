// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: List of orderService data functions and name which are linked to the orderServiceImpl


package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.additonalClasses.orderBuyerDetails;
import com.example.ECommerce_BackEnd.model.additonalClasses.order_CustomerPastOrders;
import com.example.ECommerce_BackEnd.model.additonalClasses.order_SellerOrders;
import com.example.ECommerce_BackEnd.model.orderDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface orderService {


    orderBuyerDetails findBuyerOrderDetails(String customer_username);

    String findSellerDetails(String seller_username);

    ResponseEntity<orderDetails> saveOrder(orderDetails orderDetails);

    ResponseEntity<List<order_CustomerPastOrders>> findBuyerOrderDetails2(String customerUsername);

    ResponseEntity<List<order_SellerOrders>> findSellerOrderDetails(String sellerUsername);
}
