// Programmer Name 	: Mr. Lai Khai Meng , TP055753 , APU3F2209CS
// Program Name   	: E_Commerce_Back_End
// Description     	: To allow system to perform order Service with database using repository

package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.additonalClasses.orderBuyerDetails;
import com.example.ECommerce_BackEnd.model.additonalClasses.order_CustomerPastOrders;
import com.example.ECommerce_BackEnd.model.additonalClasses.order_SellerOrders;
import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.orderDetails;
import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import com.example.ECommerce_BackEnd.repository.customerLoginRepository;
import com.example.ECommerce_BackEnd.repository.orderRepository;
import com.example.ECommerce_BackEnd.repository.productRepository;
import com.example.ECommerce_BackEnd.repository.sellerLoginRepository;
import com.example.ECommerce_BackEnd.service.orderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class orderServiceImpl implements orderService {

    private com.example.ECommerce_BackEnd.repository.orderRepository orderRepository;
    private com.example.ECommerce_BackEnd.repository.customerLoginRepository customerLoginRepository;
    private sellerLoginRepository sellerLoginRepository;

    private com.example.ECommerce_BackEnd.repository.productRepository productRepository;




    public orderServiceImpl(com.example.ECommerce_BackEnd.repository.orderRepository orderRepository, com.example.ECommerce_BackEnd.repository.customerLoginRepository customerLoginRepository, com.example.ECommerce_BackEnd.repository.sellerLoginRepository sellerLoginRepository, com.example.ECommerce_BackEnd.repository.productRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerLoginRepository = customerLoginRepository;
        this.sellerLoginRepository = sellerLoginRepository;
        this.productRepository = productRepository;
    }


    @Override
    public orderBuyerDetails findBuyerOrderDetails(String customer_username) {
        Optional<customerLogin> customerLogin = Optional.ofNullable(this.customerLoginRepository.authenticateUserLogin(customer_username));
        customerLogin customerLogin2 = customerLogin.get();
        orderBuyerDetails orderBuyerDetails = new orderBuyerDetails();
        orderBuyerDetails.setOrder_Buyer_Username(customer_username);
        orderBuyerDetails.setOrder_Buyer_ContactNumber(customerLogin2.getCustomer_phonenumber());
        orderBuyerDetails.setOrder_Delivery_Address(customerLogin2.getCustomer_address());
        return orderBuyerDetails;
    }

    @Override
    public String findSellerDetails(String seller_username) {
        Optional<sellerLogin> sellerLogin = Optional.ofNullable(this.sellerLoginRepository.findSellerInfo(seller_username));
        sellerLogin sellerLogin1 = sellerLogin.get();
        String sellerAddress = sellerLogin1.getSeller_address();
        return  sellerAddress;
    }

    @Override
    public ResponseEntity<orderDetails> saveOrder(orderDetails orderDetails) {
        Optional<product> selectedProductList = productRepository.findById(Long.valueOf(orderDetails.getProduct_id()));
        product selectedProduct = selectedProductList.get();
        int newAmount = selectedProduct.getProduct_quantity()-orderDetails.getOrder_productquantity();
        selectedProduct.setProduct_quantity(newAmount);
        return new ResponseEntity<orderDetails>(this.orderRepository.save(orderDetails), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<order_CustomerPastOrders>> findBuyerOrderDetails2(String customerUsername) {
        List<orderDetails> customerLogin = this.orderRepository.findByCustomer(customerUsername);
        List<order_CustomerPastOrders> customerPastOrderList = new ArrayList<>();

        for (orderDetails orderDetails : customerLogin) {
            order_CustomerPastOrders order_customerPastOrders = new order_CustomerPastOrders();
            order_customerPastOrders.setId_order(orderDetails.getId_order());
            order_customerPastOrders.setOrder_date(orderDetails.getOrder_date());
            order_customerPastOrders.setProduct_id(orderDetails.getProduct_id());
            order_customerPastOrders.setOrder_priceamount(orderDetails.getOrder_priceamount());
            order_customerPastOrders.setOrder_seller_username(orderDetails.getOrder_seller_username());
            order_customerPastOrders.setOrder_description(orderDetails.getOrder_description());
            order_customerPastOrders.setOrder_status(orderDetails.getOrder_status());
            order_customerPastOrders.setOrder_transaction_record(orderDetails.getOrder_transaction_record());
            Optional<product> selectedProduct = productRepository.findById((long) orderDetails.getProduct_id());
            selectedProduct.ifPresent(product -> {
                order_customerPastOrders.setProduct_name(product.getProduct_name());
            });
            customerPastOrderList.add(order_customerPastOrders);
        }

        return ResponseEntity.ok(customerPastOrderList);
    }

    @Override
    public ResponseEntity<List<order_SellerOrders>> findSellerOrderDetails(String sellerUsername) {
        List<orderDetails>  sellerSelected = this.orderRepository.findBySeller(sellerUsername);
        List<order_SellerOrders> sellerOrderList = new ArrayList<>();
        for (orderDetails orderDetails : sellerSelected) {
            order_SellerOrders order_sellerOrders = new order_SellerOrders();
            order_sellerOrders.setId_order(orderDetails.getId_order());
            order_sellerOrders.setOrder_date(orderDetails.getOrder_date());
            order_sellerOrders.setProduct_id(orderDetails.getProduct_id());
            order_sellerOrders.setOrder_priceamount(orderDetails.getOrder_priceamount());
            order_sellerOrders.setOrder_seller_username(orderDetails.getOrder_seller_username());
            order_sellerOrders.setOrder_description(orderDetails.getOrder_description());
            order_sellerOrders.setOrder_status(orderDetails.getOrder_status());
            order_sellerOrders.setOrder_transaction_record(orderDetails.getOrder_transaction_record());
            order_sellerOrders.setOrder_buyer_username(orderDetails.getOrder_buyer_username());
            sellerOrderList.add(order_sellerOrders);
        }
        return ResponseEntity.ok(sellerOrderList);
    }
}
