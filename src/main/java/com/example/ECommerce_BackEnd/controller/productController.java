package com.example.ECommerce_BackEnd.controller;

import com.example.ECommerce_BackEnd.model.imageModel;
import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.service.productService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/products")
public class productController {


    private final com.example.ECommerce_BackEnd.service.productService productService;

    public productController(com.example.ECommerce_BackEnd.service.productService productService) {
        this.productService = productService;
    }

    @PostMapping(value = {"/addNewProduct"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public product addNewProduct(@RequestPart("product") product product,
                                 @RequestPart("imageFile")MultipartFile[]file){

        try{
            Set<imageModel>imageModels= uploadImage(file);
            product.setProductImages(imageModels);
            return productService.addNewProduct(product);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<imageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<imageModel> imageModel = new HashSet<>();
        for(MultipartFile file :multipartFiles){
            imageModel imageModel1 = new imageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModel.add(imageModel1);
        }
        return imageModel;
    }

    @GetMapping({"/getAllProducts"})
    public List<product> getAllProducts(){
        return productService.getAllProducts();
    }




}
