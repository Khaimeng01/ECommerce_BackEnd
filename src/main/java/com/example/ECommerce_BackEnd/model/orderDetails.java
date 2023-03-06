package com.example.ECommerce_BackEnd.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order")
public class orderDetails {

    @Id
    @Column(name ="ID_ORDER")
    private int id_order;

    @Column(name ="PRODUCT_ID")
    private int product_id;

    @Column(name ="ORDER_PRODUCTQUANTITY")
    private int order_productquantity;

    @Column(name ="ORDER_PRICEAMOUNT")
    private int order_priceamount;

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

}
