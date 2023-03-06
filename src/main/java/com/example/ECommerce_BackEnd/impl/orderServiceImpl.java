package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.additonalClasses.orderBuyerDetails;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.orderDetails;
import com.example.ECommerce_BackEnd.service.orderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class orderServiceImpl implements orderService {

    private com.example.ECommerce_BackEnd.repository.orderRepository orderRepository;
    private com.example.ECommerce_BackEnd.repository.customerLoginRepository customerLoginRepository;

    public orderServiceImpl(com.example.ECommerce_BackEnd.repository.orderRepository orderRepository,
                            com.example.ECommerce_BackEnd.repository.customerLoginRepository customerLoginRepository) {
        this.orderRepository = orderRepository;
        this.customerLoginRepository = customerLoginRepository;
    }

    @Override
    public orderBuyerDetails findBuyerOrderDetails(String customer_username) {
        Optional<customerLogin> customerLogin =  this.customerLoginRepository.findById(customer_username);
        customerLogin customerLogin2 = customerLogin.get();
        orderBuyerDetails orderBuyerDetails = new orderBuyerDetails();
        orderBuyerDetails.setOrder_Seller_Username(customer_username);
        orderBuyerDetails.setOrder_SellerContactNumber(customerLogin2.getCustomer_phonenumber());
        orderBuyerDetails.setOrder_deliveryAddress(customerLogin2.getCustomer_address());
        return orderBuyerDetails;
    }

    @Override
    public ResponseEntity<orderDetails> saveOrder(orderDetails orderDetails) {
        return new ResponseEntity<orderDetails>(this.orderRepository.save(orderDetails), HttpStatus.CREATED);
    }
}
