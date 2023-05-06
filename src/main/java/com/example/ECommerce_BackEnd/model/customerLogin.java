// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: The customerLogin model to use for database and communication with frontend


package com.example.ECommerce_BackEnd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customerdata_tb")
public class customerLogin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_customerlogin;

    @Column(name ="CUSTOMER_USERNAME")
    private String customer_username;

    @Column(name ="CUSTOMER_PASSWORD")
    private String customer_password;

    @Column(name="CUSTOMER_EMAIL")
    private String customer_email;

    @Column(name="CUSTOMER_ADDRESS")
    private String customer_address;

    @Column(name="CUSTOMER_PHONENUMBER")
    private String customer_phonenumber;

}
