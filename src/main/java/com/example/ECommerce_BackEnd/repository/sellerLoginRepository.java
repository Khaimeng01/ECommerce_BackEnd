package com.example.ECommerce_BackEnd.repository;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface sellerLoginRepository extends JpaRepository<sellerLogin,String> {


    @Query(value = "SELECT * FROM `sellerdata` WHERE seller_username =:seller_username",nativeQuery = true)
    sellerLogin findSellerInfo(@Param("seller_username")String seller_username);
}
