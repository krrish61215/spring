package com.nagarro.exittest_spring.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nagarro.exittest_spring.entity.Product;





public interface ProductRepository extends MongoRepository<Product, String> {
	Product findProductByProductCode(String productCode);
	 List<Product> findByProductCodeContainingOrBrandContainingOrProductNameContaining(String productCode, String brand, String productName);
    
}
