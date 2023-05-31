package com.nagarro.exittest_spring.controller;



import com.nagarro.exittest_spring.entity.Product;
import com.nagarro.exittest_spring.entity.Review;
import com.nagarro.exittest_spring.service.ProductService;
import com.nagarro.exittest_spring.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;
    private ReviewService reviewService;

    @Autowired
    public ProductController(ProductService productService,ReviewService reviewService) {
        this.productService = productService;
        this.reviewService=reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        long count = productService.getCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping("/search/{query}")
    public ResponseEntity<List<Map<String, Object>>> searchProducts(@PathVariable String query) {
        List<Product> products = productService.searchProducts(query);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Product product : products) {
            Map<String, Object> productWithReviews = new HashMap<>();
            List<Review> reviews = reviewService.getReviewsByProductCode(product.getProductCode());
            productWithReviews.put("product", product);
            productWithReviews.put("reviews", reviews);
            result.add(productWithReviews);
        }

        return ResponseEntity.ok(result);
    }
    







}
