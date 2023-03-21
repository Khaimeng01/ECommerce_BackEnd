package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ECommerce_BackEnd.service.customerLoginService;

import java.util.ArrayList;
import java.util.Collections;
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


    //2. Current login
    @Override
    public String authenticateUserLogin(String customerUsername,String customerPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Optional<customerLogin> opUser = Optional.ofNullable(customerLoginRepository.authenticateUserLogin(customerUsername));
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

    //3. For Profile Management
    @Override
    public ResponseEntity<List<customerLogin>> findCustomerPersonalInformation(String customerUsername) {
        List<customerLogin> currentUserInformationList = new ArrayList<>();
        Optional<customerLogin> currentUserInformation = Optional.ofNullable(customerLoginRepository.authenticateUserLogin(customerUsername));
        currentUserInformation.ifPresent(currentUserInformationList::add);
        return currentUserInformationList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(currentUserInformationList);
    }


    //4. Register Customer
    @Override
    public ResponseEntity<customerLogin> saveCustomerLogin(customerLogin customerLogin) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bcrypt.encode(customerLogin.getCustomer_password());
        customerLogin.setCustomer_password(encryptedPassword);
        return new ResponseEntity<customerLogin>(customerLoginRepository.save(customerLogin),HttpStatus.CREATED);
    }


    //5. Update Customer
    @Override
    public ResponseEntity<String> updateCustomerData(customerLogin customerLogin, String customer_username) {
        customerLogin existingCustomerLogin = this.customerLoginRepository.findById(customer_username).orElseThrow
                (()-> new RuntimeException());
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        existingCustomerLogin.setCustomer_username(customerLogin.getCustomer_username());
          existingCustomerLogin.setCustomer_address(customerLogin.getCustomer_address());
          existingCustomerLogin.setCustomer_email(customerLogin.getCustomer_email());
          existingCustomerLogin.setCustomer_phonenumber(customerLogin.getCustomer_phonenumber());
          existingCustomerLogin.setCustomer_password(bcrypt.encode(customerLogin.getCustomer_password()));
          this.customerLoginRepository.save(existingCustomerLogin);
          String message =customerLogin.getCustomer_username();
//        customerLogin.getCustomer_username()
          return ResponseEntity.ok().body(message);
    }

    @Override
    public String findBySoorya(String customerUsername) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        customerLogin customerLogin = customerLoginRepository.authenticateUserLogin(customerUsername);
        String message = customerLogin.getCustomer_username();
//        Optional<customerLogin> opUser = Optional.ofNullable(customerLoginRepository.findByCustomer(customerUsername));
//        if(opUser.isPresent()){
//            customerLogin customerLogin = opUser.get();
//            if(bcrypt.matches(customerPassword,customerLogin.getCustomer_password())){
//                return "Authenticated User";
//            }else{
//                return "Incorect Password";
//            }
//        }
        return message;
    }


}
