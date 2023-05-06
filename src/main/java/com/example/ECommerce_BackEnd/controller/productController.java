// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: To allow Front End to communicate with Back end product services

package com.example.ECommerce_BackEnd.controller;

import com.example.ECommerce_BackEnd.model.imageModel;
import com.example.ECommerce_BackEnd.model.product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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


    @GetMapping("/getProduct")
    public List<product> getProduct(@RequestParam(value = "id_product") Long productId){
        return productService.getProduct(productId);
    }


    @GetMapping("/getProduct/FS")
    public List<product> getProductFromSeller(@RequestParam(value="product_owner") String productOwner){
        return  productService.getProductFromSeller(productOwner);
    }


    @PutMapping(value = {"/put"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> updateProductData(@RequestPart("product") product product,
                                                    @RequestPart("imageFile")MultipartFile[]file,
                                                    @RequestParam(value = "id_product") Long id_Product){

        try{
            Set<imageModel>imageModels= uploadImage(file);
            product.setProductImages(imageModels);
            return productService.updateProductData(product,id_Product);
        }catch (Exception e){
            return null;
        }
    }


    @DeleteMapping("/deleteProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam(value = "id_product") Long productId){
        return productService.deleteProduct(productId);
    }


    @GetMapping("/filterProductData")
    public List<product> getProductFromCategory(@RequestParam(value = "product_category",required = false) String product_category,
                                                @RequestParam(value = "product_priceSortingType",required = false) String product_priceSortingType){
        return productService.getProductFromCategory(product_category,product_priceSortingType);
    }






}
