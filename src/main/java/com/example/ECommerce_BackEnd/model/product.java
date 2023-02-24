package com.example.ECommerce_BackEnd.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    private String product_name;


    private int product_price;

    private String product_owner;

    private String product_quantity;

    private String product_category;

    private String product_description;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
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
