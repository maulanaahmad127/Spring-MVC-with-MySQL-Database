package com.kelaskoding.repo;

import java.util.List;

import com.kelaskoding.Entity.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> {
    
    List<Product> findByNameContains(String keyword);
}
