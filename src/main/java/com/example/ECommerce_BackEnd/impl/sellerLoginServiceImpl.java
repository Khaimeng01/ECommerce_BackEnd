package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.additonalClasses.sellerData2;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import com.example.ECommerce_BackEnd.service.additonalServices.encryptionService;
import com.example.ECommerce_BackEnd.service.sellerLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class sellerLoginServiceImpl implements sellerLoginService {

    private com.example.ECommerce_BackEnd.repository.sellerLoginRepository sellerLoginRepository;
    private encryptionService encryptionService;

//    public sellerLoginServiceImpl(com.example.ECommerce_BackEnd.repository.sellerLoginRepository sellerLoginRepository) {
//        this.sellerLoginRepository = sellerLoginRepository;
//    }


    public sellerLoginServiceImpl(com.example.ECommerce_BackEnd.repository.sellerLoginRepository sellerLoginRepository, com.example.ECommerce_BackEnd.service.additonalServices.encryptionService encryptionService) {
        this.sellerLoginRepository = sellerLoginRepository;
        this.encryptionService = encryptionService;
    }

    //1. Current login
    @Override
    public String authenticateSellerLogin(String sellerUsername, String sellerPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        sellerLogin sellerLogin = sellerLoginRepository.findSellerInfo(sellerUsername);
        if(sellerLogin == null){
            return "No user is found with this Username";
        }else{
            if(bcrypt.matches(sellerPassword,sellerLogin.getSeller_password())){
                return "Authenticated User";
            }else{
                return "Incorect Password";
            }
        }
    }

    //2. Register Seller
    @Override
    public ResponseEntity<String> saveSellerLogin(sellerData2 sellerData2) throws Exception {

        List<sellerLogin> sellerLoginList = sellerLoginRepository.findAll();
        Boolean status = false;
        for(int i=0;i< sellerLoginList.size();i++){
            sellerLogin sellerLoginFromList = sellerLoginList.get(i);
            if(Objects.equals(sellerLoginFromList.getSeller_username(), sellerData2.getSeller_username())){
                status = true;
            }
        }

        if(!status){
            //Encrypt Password
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptedPassword = bcrypt.encode(sellerData2.getSeller_password());
            sellerData2.setSeller_password(encryptedPassword);

            //Encrypt Wallet Address
            byte[] encryptedBytes = encryptionService.encrypt(sellerData2.getSeller_accountdetails());
            sellerLogin sellerLoginLoginData = new sellerLogin();
            sellerLoginLoginData.setSeller_username(sellerData2.getSeller_username());
            sellerLoginLoginData.setSeller_password(sellerData2.getSeller_password());
            sellerLoginLoginData.setSeller_address(sellerData2.getSeller_address());
            sellerLoginLoginData.setSeller_phonenumber(sellerData2.getSeller_phonenumber());
            sellerLoginLoginData.setSeller_email(sellerData2.getSeller_email());

            sellerLoginLoginData.setSeller_accountdetails(sellerData2.getSeller_accountdetails());

            sellerLoginRepository.save(sellerLoginLoginData);

            return ResponseEntity.ok().body("Success");

        }else{
            return ResponseEntity.ok().body("Username has been used");
        }

    }

    //3. For Profile Management
    @Override
    public ResponseEntity<List<sellerLogin>> findSellerPersonalInformation(String sellerUsername) throws Exception {

        sellerLogin sellerLoginData = sellerLoginRepository.findSellerInfo(sellerUsername);
        List<sellerLogin> currentUserInformationList = new ArrayList<>();
        currentUserInformationList.add(sellerLoginData);
        return ResponseEntity.ok(currentUserInformationList);

    }

    //4. Update Seller Data [Profile Management]
    @Override
    public ResponseEntity<String> updateSellerData(sellerLogin sellerLogin, String sellerUsername) {
        sellerLogin existingSeller = this.sellerLoginRepository.findSellerInfo(sellerUsername);
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        existingSeller.setSeller_username(sellerLogin.getSeller_username());
        existingSeller.setSeller_address(sellerLogin.getSeller_address());
        existingSeller.setSeller_email(sellerLogin.getSeller_email());
        existingSeller.setSeller_phonenumber(sellerLogin.getSeller_phonenumber());

        if(!sellerLogin.getSeller_password().equals(existingSeller.getSeller_password())){
            String encryptedPassword = bcrypt.encode(sellerLogin.getSeller_password());
            existingSeller.setSeller_accountdetails(encryptedPassword);
            if(!sellerLogin.getSeller_accountdetails().equals(existingSeller.getSeller_accountdetails())){
                existingSeller.setSeller_accountdetails(sellerLogin.getSeller_accountdetails());
            }
        }

        this.sellerLoginRepository.save(existingSeller);
        String message = "Data save successfully";
        return ResponseEntity.ok().body(message);
    }





}
