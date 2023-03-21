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
