package com.nagarro.exittest_spring.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import com.nagarro.exittest_spring.entity.Product;
import com.nagarro.exittest_spring.entity.Review;
import com.nagarro.exittest_spring.repo.ProductRepository;
import com.nagarro.exittest_spring.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,ReviewRepository reviewRepository ) {
        this.productRepository = productRepository;
        this.reviewRepository= reviewRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public long getCount() {
        return productRepository.count();
    }

    public List<Product> searchProducts(String query) {
        List<Product> results = new ArrayList<>();
        String[] words = query.split(" ");
        for(String word: words){
            List<Product> tempResults = productRepository.findByProductCodeContainingOrBrandContainingOrProductNameContaining(word, word, word);
            results.addAll(tempResults);
        }
        // Remove duplicates from results
        results = results.stream().distinct().collect(Collectors.toList());
        for(Product product : results) {
            List<Review> reviews = reviewRepository.findByProductCode(product.getProductCode());
            double averageRating = reviews.stream()
            	    .filter(Review::isApproved)
            	    .mapToDouble(Review::getRating)
            	    .average()
            	    .orElse(0.0);
            product.setAverageRating(averageRating);
        }
        return results;
    }
}
