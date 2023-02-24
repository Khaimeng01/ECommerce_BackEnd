package com.example.ECommerce_BackEnd.controller;


import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import com.example.ECommerce_BackEnd.service.sellerLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/sell/dataman")
public class sellerLoginController {

    private final com.example.ECommerce_BackEnd.service.sellerLoginService sellerLoginService;

    public sellerLoginController(com.example.ECommerce_BackEnd.service.sellerLoginService sellerLoginService) {
        this.sellerLoginService = sellerLoginService;
    }

    @GetMapping("/get")
    public String findByPara32(@RequestParam(value = "seller_username") String sellerUsername,
                               @RequestParam(value = "seller_password") String sellerPassword){
        return sellerLoginService.findById(sellerUsername,sellerPassword);
    }

    @PostMapping("/post")
    public ResponseEntity<sellerLogin> saveCustomerLogin(@RequestBody sellerLogin sellerLogin){
        return sellerLoginService.saveSellerLogin(sellerLogin);
    }
}
