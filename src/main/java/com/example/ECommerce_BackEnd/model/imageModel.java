package com.example.ECommerce_BackEnd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "image_model_tb")
public class imageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long image_model_id;
    private String image_model_name;
    private String image_model_image_type;

    @Column(length = 50000000)
    private byte[] image_model_pic_byte;




    public imageModel(String image_model_name, String image_model_image_type, byte[] image_model_pic_byte) {
        this.image_model_name = image_model_name;
        this.image_model_image_type = image_model_image_type;
        this.image_model_pic_byte = image_model_pic_byte;
    }

    public imageModel() {

    }

    public Long getImage_model_id() {
        return image_model_id;
    }

    public void setImage_model_id(Long image_model_id) {
        this.image_model_id = image_model_id;
    }

    public String getImage_model_name() {
        return image_model_name;
    }

    public void setImage_model_name(String image_model_name) {
        this.image_model_name = image_model_name;
    }

    public String getImage_model_image_type() {
        return image_model_image_type;
    }

    public void setImage_model_image_type(String image_model_image_type) {
        this.image_model_image_type = image_model_image_type;
    }

    public byte[] getImage_model_pic_byte() {
        return image_model_pic_byte;
    }

    public void setImage_model_pic_byte(byte[] image_model_pic_byte) {
        this.image_model_pic_byte = image_model_pic_byte;
    }

}
