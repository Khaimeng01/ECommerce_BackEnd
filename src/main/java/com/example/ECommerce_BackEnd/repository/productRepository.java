package com.example.ECommerce_BackEnd.repository;

import com.example.ECommerce_BackEnd.model.product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends CrudRepository<product,Long> {
}
