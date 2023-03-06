package com.example.ECommerce_BackEnd.service;

import com.example.ECommerce_BackEnd.model.additonalClasses.orderBuyerDetails;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.orderDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface orderService {

    orderBuyerDetails findBuyerOrderDetails(String customer_username);

    ResponseEntity<orderDetails> saveOrder(orderDetails orderDetails);


}
