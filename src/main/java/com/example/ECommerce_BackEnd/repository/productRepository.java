package com.example.ECommerce_BackEnd.repository;

import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface productRepository extends CrudRepository<product,Long> {

    @Query(value = "SELECT * FROM `product_tb` WHERE product_owner =:seller_username",nativeQuery = true)
    List<product> getProductFromSeller(@Param("seller_username")String seller_username);

    @Query(value = "SELECT * FROM `product_tb` WHERE product_category =:product_category",nativeQuery = true)
    List<product> getProductFromCategory(@Param("product_category") String product_category);

}
