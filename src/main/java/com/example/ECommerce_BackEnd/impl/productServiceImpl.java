package com.example.ECommerce_BackEnd.impl;

import com.example.ECommerce_BackEnd.model.customerLogin;
import com.example.ECommerce_BackEnd.model.product;
import com.example.ECommerce_BackEnd.model.sellerLogin;
import com.example.ECommerce_BackEnd.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class productServiceImpl implements productService {

    private com.example.ECommerce_BackEnd.repository.productRepository productRepository;

    @Autowired
    public productServiceImpl(com.example.ECommerce_BackEnd.repository.productRepository productRepository) {
        this.productRepository = productRepository;
    }

    //    1.Register New Product
    @Override
    public product addNewProduct(product product) {
        return productRepository.save(product);
    }

    //    2.Get All Products
    @Override
    public List<product> getAllProducts() {
        return (List<product>) productRepository.findAll();
    }

    //    3.Get Specific Product
    @Override
    public List<product> getProduct(long productId) {
        Optional<product> p = productRepository.findById(productId);
        return p.map(Collections::singletonList).orElse(Collections.emptyList());
    }



    //    4. Get Product for Specific Seller
    @Override
    public List<product> getProductFromSeller(String productOwner) {
        List<product> productsOfSeller = productRepository.getProductFromSeller(productOwner);
        return productsOfSeller;
//        Optional<product> p = (Optional<product>) Optional.ofNullable(productRepository.getProductFromSeller(productOwner));
//        p.ifPresent(productsOfSeller::add);
//        return productsOfSeller.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(productsOfSeller);
    }

    //  5.Edit Product Details
    @Override
    public ResponseEntity<String> updateProductData(product product, Long productId) {
        Optional<com.example.ECommerce_BackEnd.model.product> existingProductList = this.productRepository.findById(productId);
        product selectedProduct = existingProductList.get();
        selectedProduct.setProduct_name(product.getProduct_name());
        selectedProduct.setProduct_category(product.getProduct_category());
        selectedProduct.setProduct_quantity(product.getProduct_quantity());
        selectedProduct.setProduct_owner(product.getProduct_owner());
        selectedProduct.setProduct_description(product.getProduct_description());
        selectedProduct.setProductImages(product.getProductImages());
        selectedProduct.setProduct_price(product.getProduct_price());
        this.productRepository.save(selectedProduct);
        String message = "Data save successfully";
        return ResponseEntity.ok().body(message);
    }

    //  6. Delete Product
    @Override
    public ResponseEntity<String> deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
        String message = "Success";
        return ResponseEntity.ok().body(message);
    }

    //  7. Find Product from Specific Category
    @Override
//    public List<product> getProductFromCategory(String product_category,String sortingType) {
//        List<product> listOfProducts;
//        if(StringUtils.isEmpty(product_category)){
//            listOfProducts = productRepository.getProductFromCategory(product_category);
//            if(StringUtils.isEmpty(sortingType)){
//                if(sortingType.equals("ASCENDING")){
//                    listOfProducts.sort(Comparator.comparing(product::getProduct_price));
//                }else{
//                    listOfProducts.sort(Comparator.comparing(product::getProduct_price).reversed());
//                }
//                return  listOfProducts;
//            }else{
//                return listOfProducts;
//            }
//        }else{
//            listOfProducts= (List<product>) productRepository.findAll();
//            if(Objects.equals(sortingType, "ASCENDING")){
//                listOfProducts.sort(Comparator.comparing(product::getProduct_price));
//            }else{
//                listOfProducts.sort(Comparator.comparing(product::getProduct_price).reversed());
//            }
//            return listOfProducts;
//        }
//    }

    public List<product> getProductFromCategory(String product_category, String product_priceSortingType) {
        List<product> listOfProducts;
        if (!StringUtils.isEmpty(product_category)) {
            listOfProducts = productRepository.getProductFromCategory(product_category);
        } else {
            listOfProducts = (List<product>) productRepository.findAll();
        }

        if (!StringUtils.isEmpty(product_priceSortingType)) {
            if (product_priceSortingType.equals("ASCENDING")) {
                listOfProducts.sort(Comparator.comparing(product::getProduct_price));
            } else {
                listOfProducts.sort(Comparator.comparing(product::getProduct_price).reversed());
            }
        }

        return listOfProducts;
    }



//    sellerLogin existingSeller = this.sellerLoginRepository.findSellerInfo(sellerUsername);
//    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
//        existingSeller.setSeller_username(sellerLogin.getSeller_username());
//        existingSeller.setSeller_address(sellerLogin.getSeller_address());
//        existingSeller.setSeller_email(sellerLogin.getSeller_email());
//        existingSeller.setSeller_phonenumber(sellerLogin.getSeller_phonenumber());
//
//        if(!sellerLogin.getSeller_password().equals(existingSeller.getSeller_password())){
//        String encryptedPassword = bcrypt.encode(sellerLogin.getSeller_password());
//        existingSeller.setSeller_accountdetails(encryptedPassword);
//        if(!sellerLogin.getSeller_accountdetails().equals(existingSeller.getSeller_accountdetails())){
//            existingSeller.setSeller_accountdetails(sellerLogin.getSeller_accountdetails());
//        }
//    }
//
//        this.sellerLoginRepository.save(existingSeller);
//    String message = "Data save successfully";
//        return ResponseEntity.ok().body(message);


//    //    Edit Product Details
//    @PutMapping("/put")
//    public ResponseEntity<String> updateProductData(@RequestBody product product, @RequestParam(value = "id_Product") Long id_Product){
//        return productService.updateProductData(product,id_Product);
//    }

}
