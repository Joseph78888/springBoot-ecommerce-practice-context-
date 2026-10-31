package com.example.ecomerce_application.controller;

import com.example.ecomerce_application.model.Product;
import com.example.ecomerce_application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin // let the backend and front connect from defrent ports
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{prodId}")
    public Product getProductById(@PathVariable("prodId") int prodId) {
        return productService.getProductById(prodId);
    }
}
