package com.rymar.service;

import com.rymar.entity.Product;
import com.rymar.repo.spring.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(UUID id) {
        productRepo.deleteById(id);
    }
}