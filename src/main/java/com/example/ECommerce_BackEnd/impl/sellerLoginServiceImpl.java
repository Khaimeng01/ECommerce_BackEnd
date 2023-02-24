package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import com.example.ECommerce_BackEnd.repository.sellerLoginRepository;
import com.example.ECommerce_BackEnd.service.sellerLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class sellerLoginServiceImpl implements sellerLoginService {

    private com.example.ECommerce_BackEnd.repository.sellerLoginRepository sellerLoginRepository;

    public sellerLoginServiceImpl(com.example.ECommerce_BackEnd.repository.sellerLoginRepository sellerLoginRepository) {
        this.sellerLoginRepository = sellerLoginRepository;
    }

    @Override
    public String findById(String sellerUsername, String sellerPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        Optional<sellerLogin> opUser = sellerLoginRepository.findById(sellerUsername);
        if(opUser.isPresent()){
            sellerLogin sellerLogin =  opUser.get();

            if(bcrypt.matches(sellerPassword,sellerLogin.getSeller_password())){
                return "Authenticated User";
            }else{
                return "Incorect Password";
            }
        }
        return "No user is found with this Username";
    }

    @Override
    public ResponseEntity<sellerLogin> saveSellerLogin(sellerLogin sellerLogin) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bcrypt.encode(sellerLogin.getSeller_password());
        sellerLogin.setSeller_password(encryptedPassword);
        String encryptedAccountDetails = bcrypt.encode(sellerLogin.getSeller_accountdetails());
        sellerLogin.setSeller_accountdetails(encryptedAccountDetails);
        return new ResponseEntity<sellerLogin>(sellerLoginRepository.save(sellerLogin), HttpStatus.CREATED);
    }
}
