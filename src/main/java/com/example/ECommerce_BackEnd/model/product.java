// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: The product model to use for database and communication with frontend


package com.example.ECommerce_BackEnd.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@Table(name = "product_tb")
public class product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    private String product_name;


    private BigDecimal product_price;

    private String product_owner;

    //Need to change to int
    private int product_quantity;

    private String product_category;

    private String product_description;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "product_images_tb",
            joinColumns = {
                @JoinColumn(name = "id_product")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "image_id")
            }
    )
    private Set<imageModel> productImages;

    public Set<imageModel> getProductImages() {
        return productImages;
    }



    public void setProductImages(Set<imageModel> productImages) {
        this.productImages = productImages;
    }
}
