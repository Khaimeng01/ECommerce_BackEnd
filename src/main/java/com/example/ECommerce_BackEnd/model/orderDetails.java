package com.example.ECommerce_BackEnd.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "order_tb")
public class orderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;

    @Column(name = "ORDER_DATE")
    private Date order_date;

    @Column(name ="PRODUCT_ID")
    private int product_id;

    @Column(name ="ORDER_PRODUCTQUANTITY")
    private int order_productquantity;

    @Column(name ="ORDER_PRICEAMOUNT")
    private BigDecimal order_priceamount;

    @Column(name ="ORDER_BUYER_USERNAME")
    private String order_buyer_username;

    @Column(name ="ORDER_SELLER_USERNAME")
    private String order_seller_username;

    @Column(name = "ORDER_DELIVERY_ADDRESS")
    private String order_delivery_address;

    @Column(name = "ORDER_BUYER_CONTACT_NUMBER")
    private String order_buyer_contact_number;


    @Column(name ="ORDER_DESCRIPTION")
    private String order_description;

    @Column(name ="ORDER_STATUS")
    private String order_status;

    @Column(name="ORDER_TRANSACTION_RECORD")
    private String order_transaction_record;

}
