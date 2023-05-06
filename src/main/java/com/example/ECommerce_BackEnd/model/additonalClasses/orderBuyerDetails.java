// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: The orderBuyerDetails model to use for database and communication with frontend


package com.example.ECommerce_BackEnd.model.additonalClasses;

import lombok.Data;

@Data
public class orderBuyerDetails {

    private String order_Buyer_Username;
    private String order_Delivery_Address;
    private String order_Buyer_ContactNumber;
}


