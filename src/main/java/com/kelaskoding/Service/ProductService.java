package com.kelaskoding.Service;

import java.util.List;
import java.util.Optional;

import com.kelaskoding.Entity.Product;
import com.kelaskoding.repo.ProductRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    ProductRepo repo;

    @Autowired
    private EmailService emailService;
        
   

    public Iterable<Product> findAll(){
        return repo.findAll();
    }

    public void add(Product product){
        emailService.sendEmail("alfian@gmail.com", "You have added new product", "Hi you have added your new product : " + product.getCode() + " - " + product.getName());
        repo.save(product);
    }

    public void deleteById(long id){
        repo.deleteById(id);
    }

    public Optional<Product> findById(long id){
       return repo.findById(id);
    }

    public void edit(Product product){
        repo.save(product);
    }

    public List<Product> findByName(String key){
        return repo.findByNameContains(key);
    }
}
