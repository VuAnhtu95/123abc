package com.example.demo.services.product;

import com.example.demo.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product product);
    void deleteProduct(Product product);
}
