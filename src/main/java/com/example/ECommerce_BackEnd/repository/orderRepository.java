package com.example.ECommerce_BackEnd.repository;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.orderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<orderDetails,Integer> {
}
