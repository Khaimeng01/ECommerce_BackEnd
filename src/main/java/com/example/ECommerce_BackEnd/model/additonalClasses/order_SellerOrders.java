// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: The order_SellerOrders model to use for database and communication with frontend

package com.example.ECommerce_BackEnd.model.additonalClasses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class order_SellerOrders {
    private int id_order;
    private Date order_date;
    private int product_id;
    private BigDecimal order_priceamount;
    private String order_buyer_username;
    private String order_seller_username;
    private String order_description;
    private String order_status;
    private String order_transaction_record;
}
