package com.example.ecomerce_application.service;

import com.example.ecomerce_application.model.Product;
import com.example.ecomerce_application.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int prodId) {
        return productRepo.findById(prodId).orElse(null);
    }
}
