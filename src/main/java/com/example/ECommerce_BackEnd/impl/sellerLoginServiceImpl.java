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


    public sellerLoginServiceImpl(com.example.ECommerce_BackEnd.repository.sellerLoginRepository sellerLoginRepository, com.example.ECommerce_BackEnd.service.additonalServices.encryptionService encryptionService) {
        this.sellerLoginRepository = sellerLoginRepository;
        this.encryptionService = encryptionService;
    }

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
                return ("Incorect Password"+sellerLogin.getSeller_username());
            }
        }
    }


    @Override
    public ResponseEntity<String> saveSellerLogin(sellerData2 sellerData2) throws Exception {

        List<sellerLogin> sellerLoginList = sellerLoginRepository.findAll();
        int status = 0;
        String bodyMessage = null;
        for(int i=0;i< sellerLoginList.size();i++){
            sellerLogin sellerLoginFromList = sellerLoginList.get(i);
            if(Objects.equals(sellerLoginFromList.getSeller_username(), sellerData2.getSeller_username())){
                status = 1;
            } else if (Objects.equals(sellerLoginFromList.getSeller_email(), sellerData2.getSeller_email())) {
                status = 2;
            }
        }

        if(status==0){

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptedPassword = bcrypt.encode(sellerData2.getSeller_password());
            sellerData2.setSeller_password(encryptedPassword);
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
            if(status ==1){
                bodyMessage = "Username has been used";
            } else if (status ==2) {
                bodyMessage = "Email has been used";
            }
            return ResponseEntity.ok().body(bodyMessage);
        }

    }


    @Override
    public ResponseEntity<List<sellerLogin>> findSellerPersonalInformation(String sellerUsername) throws Exception {

        sellerLogin sellerLoginData = sellerLoginRepository.findSellerInfo(sellerUsername);
        List<sellerLogin> currentUserInformationList = new ArrayList<>();
        currentUserInformationList.add(sellerLoginData);
        return ResponseEntity.ok(currentUserInformationList);

    }


    @Override
    public ResponseEntity<String> updateSellerData(sellerLogin sellerLogin, String sellerUsername) {
        sellerLogin existingSeller = this.sellerLoginRepository.findSellerInfo(sellerUsername);
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        existingSeller.setSeller_username(sellerLogin.getSeller_username());
        existingSeller.setSeller_address(sellerLogin.getSeller_address());
        existingSeller.setSeller_email(sellerLogin.getSeller_email());
        existingSeller.setSeller_phonenumber(sellerLogin.getSeller_phonenumber());
        existingSeller.setSeller_accountdetails(sellerLogin.getSeller_accountdetails());

        if(!sellerLogin.getSeller_password().equals(existingSeller.getSeller_password())){
            String encryptedPassword = bcrypt.encode(sellerLogin.getSeller_password());
            existingSeller.setSeller_password(encryptedPassword);
        }

        this.sellerLoginRepository.save(existingSeller);
        String message = "Data save successfully";
        return ResponseEntity.ok().body(message);
    }

    @Override
    public ResponseEntity<String> findIfAccountExists(String sellerUsername) {
        String message = "";
        try{
            sellerLogin sellerLogin = sellerLoginRepository.findSellerInfo(sellerUsername);
            if(sellerLogin!=null){
                message = "Account Does Exits";
            }
        }catch (Exception e){
            message = "Account Does Not Exits";
        }
        return ResponseEntity.ok().body(message);
    }

    @Override
    public ResponseEntity<String> updateSellerPassword(String sellerUsername, String sellerPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        sellerLogin sellerLogin = sellerLoginRepository.findSellerInfo(sellerUsername);
        sellerLogin.setSeller_password(bcrypt.encode(sellerPassword));
        this.sellerLoginRepository.save(sellerLogin);
        String message = "Data save successfully";
        return ResponseEntity.ok().body(message);
    }


}
