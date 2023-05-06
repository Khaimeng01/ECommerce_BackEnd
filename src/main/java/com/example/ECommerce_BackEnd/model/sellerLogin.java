// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: The sellerLogin model to use for database and communication with frontend



package com.example.ECommerce_BackEnd.model;


import com.example.ECommerce_BackEnd.service.additonalServices.AesEncryptor;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sellerdata_tb")
public class sellerLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sellerlogin;

    @Column(name ="SELLER_USERNAME")
    private String seller_username;

    @Column(name ="SELLER_PASSWORD")
    private String seller_password;

    @Convert(converter = AesEncryptor.class)
    @Column(name="SELLER_ACCOUNTDETAILS")
    private String seller_accountdetails;


    @Column(name="SELLER_EMAIL")
    private String seller_email;

    @Column(name="SELLER_ADDRESS")
    private String seller_address;

    @Column(name="SELLER_PHONENUMBER")
    private String seller_phonenumber;
}
