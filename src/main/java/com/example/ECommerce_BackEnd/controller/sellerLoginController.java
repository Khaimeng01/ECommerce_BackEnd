package com.example.ECommerce_BackEnd.controller;


import com.example.ECommerce_BackEnd.model.additonalClasses.sellerData2;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/sell/dataman")
public class sellerLoginController {

    private final com.example.ECommerce_BackEnd.service.sellerLoginService sellerLoginService;

    public sellerLoginController(com.example.ECommerce_BackEnd.service.sellerLoginService sellerLoginService) {
        this.sellerLoginService = sellerLoginService;
    }

    //1. Current login
    @GetMapping("/get")
    public String authenticateSellerLogin(@RequestParam(value = "seller_username") String sellerUsername,
                               @RequestParam(value = "seller_password") String sellerPassword){
        return sellerLoginService.authenticateSellerLogin(sellerUsername,sellerPassword);
    }

    //2. Register Seller
    @PostMapping("/post")
    public ResponseEntity<String> saveCustomerLogin(@RequestBody sellerData2 sellerData2) throws Exception {
        return sellerLoginService.saveSellerLogin(sellerData2);
    }

    //3. For Profile Management
    @GetMapping("/get/FSPI")
    public ResponseEntity<List<sellerLogin>> findSellerPersonalInformation(@RequestParam(value = "seller_username") String sellerUsername) throws Exception {
        return sellerLoginService.findSellerPersonalInformation(sellerUsername);
    }

    //5. Update Seller Data [Profile Management]
    @PutMapping("/put")
    public ResponseEntity<String> updateSellerData(@RequestBody sellerLogin sellerLogin, @RequestParam(value = "seller_username") String sellerUsername){
        return sellerLoginService.updateSellerData(sellerLogin,sellerUsername);
    }


}
