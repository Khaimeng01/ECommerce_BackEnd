// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: Input specific SQL Query to obtain information from the set database in customerLogin


package com.example.ECommerce_BackEnd.repository;

import com.example.ECommerce_BackEnd.model.customerLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface customerLoginRepository extends JpaRepository<customerLogin,String> {

    @Query(value = "SELECT * FROM `customerdata_tb` WHERE customer_username =:customer_username",nativeQuery = true)
    customerLogin authenticateUserLogin(@Param("customer_username")String customer_username);


}
