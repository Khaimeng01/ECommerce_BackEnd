package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ECommerce_BackEnd.service.customerLoginService;

import java.util.*;


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



    @Override
    public String authenticateUserLogin(String customerUsername,String customerPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Optional<customerLogin> opUser = Optional.ofNullable(customerLoginRepository.authenticateUserLogin(customerUsername));
        if(opUser.isPresent()){
            customerLogin customerLogin = opUser.get();
            if(bcrypt.matches(customerPassword,customerLogin.getCustomer_password())){
                return "Authenticated User";
            }else{
                return "Incorrect Password";
            }
        }else{
            return "No user is found with this Username";
        }

    }


    @Override
    public ResponseEntity<List<customerLogin>> findCustomerPersonalInformation(String customerUsername) {
        List<customerLogin> currentUserInformationList = new ArrayList<>();
        Optional<customerLogin> currentUserInformation = Optional.ofNullable(customerLoginRepository.authenticateUserLogin(customerUsername));
        currentUserInformation.ifPresent(currentUserInformationList::add);
        return currentUserInformationList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(currentUserInformationList);
    }



    @Override
    public ResponseEntity<String> saveCustomerLogin(customerLogin customerLogin) {
        List<customerLogin> customerLoginList = customerLoginRepository.findAll();
        int status = 0;
        String bodyMessage = null;
        for(int i=0;i< customerLoginList.size();i++){
           customerLogin customerFromList = customerLoginList.get(i);
           if(Objects.equals(customerFromList.getCustomer_username(), customerLogin.getCustomer_username())){
               status = 1;
           } else if (Objects.equals(customerFromList.getCustomer_email(), customerLogin.getCustomer_email())) {
               status = 2;
           }

        }
        if(status ==0){
            Optional<customerLogin> opUser = Optional.ofNullable(customerLoginRepository.authenticateUserLogin(customerLogin.getCustomer_username()));
            if(opUser.isPresent()){
                customerLogin customerLogin_2 = opUser.get();
            }
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptedPassword = bcrypt.encode(customerLogin.getCustomer_password());
            customerLogin.setCustomer_password(encryptedPassword);
            customerLoginRepository.save(customerLogin);
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
    public ResponseEntity<String> updateCustomerData(customerLogin customerLogin, String customer_username) {
        customerLogin existingCustomerLogin = this.customerLoginRepository.authenticateUserLogin(customer_username);
          BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
          existingCustomerLogin.setCustomer_username(customerLogin.getCustomer_username());
          existingCustomerLogin.setCustomer_address(customerLogin.getCustomer_address());
          existingCustomerLogin.setCustomer_email(customerLogin.getCustomer_email());
          existingCustomerLogin.setCustomer_phonenumber(customerLogin.getCustomer_phonenumber());
          if(!customerLogin.getCustomer_password().equals(existingCustomerLogin.getCustomer_password())){
              existingCustomerLogin.setCustomer_password(bcrypt.encode(customerLogin.getCustomer_password()));

          }

          this.customerLoginRepository.save(existingCustomerLogin);
          String message = "Data save successfully";

        return ResponseEntity.ok().body(message);
    }

    @Override
    public ResponseEntity<String> findIfAccountExists(String customerUsername) {
        String message = "";
        try{
            customerLogin customerLogin = customerLoginRepository.authenticateUserLogin(customerUsername);
            if(customerLogin!=null){
                message = "Account Does Exits";
            }
        }catch (Exception e){
            message = "Account Does Not Exits";
        }
        return ResponseEntity.ok().body(message);
    }

    @Override
    public ResponseEntity<String> updateCustomerPassword(String customerUsername, String customerPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        customerLogin customerLogin = customerLoginRepository.authenticateUserLogin(customerUsername);
        customerLogin.setCustomer_password(bcrypt.encode(customerPassword));
        this.customerLoginRepository.save(customerLogin);
        String message = "Data save successfully";
        return ResponseEntity.ok().body(message);
    }


}
