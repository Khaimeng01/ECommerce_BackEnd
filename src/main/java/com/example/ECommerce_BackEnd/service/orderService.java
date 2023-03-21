package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.additonalClasses.orderBuyerDetails;
import com.example.ECommerce_BackEnd.model.additonalClasses.order_CustomerPastOrders;
import com.example.ECommerce_BackEnd.model.additonalClasses.order_SellerOrders;
import com.example.ECommerce_BackEnd.model.orderDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface orderService {

    //1. Get customerData in CheckOutPage
    orderBuyerDetails findBuyerOrderDetails(String customer_username);

    String findSellerDetails(String seller_username);

    ResponseEntity<orderDetails> saveOrder(orderDetails orderDetails);


    //   4. Get Customer Past order HHistory [Buyer]
    ResponseEntity<List<order_CustomerPastOrders>> findBuyerOrderDetails2(String customerUsername);

    //   5. Get Seller Past Order History [Seller]
    ResponseEntity<List<order_SellerOrders>> findSellerOrderDetails(String sellerUsername);
}
