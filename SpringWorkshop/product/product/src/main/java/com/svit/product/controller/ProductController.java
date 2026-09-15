package com.svit.product.controller;

import com.svit.product.entity.Product;
import com.svit.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public String saveProduct(@RequestBody Product product) {
        productService.add(product);
        return "product inserted.";
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<> (productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }
}