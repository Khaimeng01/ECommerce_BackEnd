// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: The sellerData2 model to use for database and communication with frontend


package com.example.ECommerce_BackEnd.model.additonalClasses;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class sellerData2 {


    private String seller_username;

    private String seller_password;

    private String seller_accountdetails;


    private String seller_email;


    private String seller_address;

    private String seller_phonenumber;
}
