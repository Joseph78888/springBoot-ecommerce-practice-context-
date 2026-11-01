package com.example.ecomerce_application.controller;

import com.example.ecomerce_application.model.Product;
import com.example.ecomerce_application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin // let the backend and front connect from defrent ports
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable("prodId") int prodId) {
        Product product = productService.getProductById(prodId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Product product1 = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product/{prodId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable("prodId") int prodId) {
        Product product = productService.getProductById(prodId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{prodId}")
    public ResponseEntity<String> updateProduct(@PathVariable int prodId, @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile) {
        Product product1 = null;
        try {
            product1 = productService.updateProduct(prodId, product, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("failed to update", HttpStatus.BAD_REQUEST);
        }
        if (product1 != null) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("failed to update", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int prodId) {
        Product product = productService.getProductById(prodId);
        if (product != null) {
            productService.deleteProduct(prodId);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        }
        else  {
            return new ResponseEntity<>("failed to delete", HttpStatus.NOT_FOUND);
        }
    }
}
