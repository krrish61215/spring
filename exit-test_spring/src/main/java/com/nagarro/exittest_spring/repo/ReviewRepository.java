package com.nagarro.exittest_spring.repo;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.nagarro.exittest_spring.entity.Review;
import java.util.List;


public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByProductCode(String productCode);
    List<Review> findByApproved(boolean approved);
    long countByApprovedTrue();

}
