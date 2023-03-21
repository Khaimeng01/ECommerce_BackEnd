package com.example.ECommerce_BackEnd.repository;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.orderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface orderRepository extends JpaRepository<orderDetails,Integer> {

    @Query(value = "SELECT * FROM `order_tb` WHERE order_buyer_username =:customer_username",nativeQuery = true)
    List<orderDetails> findByCustomer(@Param("customer_username")String customer_username);

    @Query(value = "SELECT * FROM `order_tb` WHERE order_seller_username =:seller_username",nativeQuery = true)
    List<orderDetails> findBySeller(@Param("seller_username")String seller_username);

}
