package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.customerLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ECommerce_BackEnd.service.customerLoginService;

import java.util.List;
import java.util.Optional;


@Service
public class customerLoginServiceImpl implements customerLoginService {

    private com.example.ECommerce_BackEnd.repository.customerLoginRepository customerLoginRepository;

    public customerLoginServiceImpl(com.example.ECommerce_BackEnd.repository.customerLoginRepository customerLoginRepository) {
        super();
        this.customerLoginRepository = customerLoginRepository;
    }


    @Override
    public List<customerLogin> findAllUsers() {
        return customerLoginRepository.findAll();
    }

//    @Override
//    public ResponseEntity<customerLogin> findById(Integer customerId) throws RuntimeException {
//        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//        return new ResponseEntity<customerLogin>(customerLoginRepository.findById(customerId)
//                .orElseThrow(()-> new RuntimeException()),
//                HttpStatus.OK);
//    }

    @Override
    public String findById2(String customerUsername,String customerPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

        Optional<customerLogin> opUser = customerLoginRepository.findById(customerUsername);
        if(opUser.isPresent()){
            customerLogin customerLogin = opUser.get();
            if(bcrypt.matches(customerPassword,customerLogin.getCustomer_password())){
                return "Authenticated User";
            }else{
                return "Incorect Password";
            }
        }
        return "No user is found with this Username";
    }

    @Override
    public ResponseEntity<customerLogin> saveCustomerLogin(customerLogin customerLogin) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bcrypt.encode(customerLogin.getCustomer_password());
        customerLogin.setCustomer_password(encryptedPassword);
        return new ResponseEntity<customerLogin>(customerLoginRepository.save(customerLogin),HttpStatus.CREATED);
    }


}
