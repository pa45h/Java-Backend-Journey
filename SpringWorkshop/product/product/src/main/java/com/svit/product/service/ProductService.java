package com.svit.product.service;

import com.svit.product.entity.Product;
import com.svit.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void add(Product product){
        productRepository.save(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return  productRepository.getById(id);
    }
}
