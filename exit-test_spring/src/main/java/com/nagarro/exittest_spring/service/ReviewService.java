package com.nagarro.exittest_spring.service;

import com.nagarro.exittest_spring.entity.Review;
import com.nagarro.exittest_spring.repo.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByProductCode(String productCode) {
    	return reviewRepository.findByProductCode(productCode).stream()
                .filter(Review::isApproved)
                .collect(Collectors.toList());
    }

    public List<Review> getUnapprovedReviews() {
        return reviewRepository.findByApproved(false);
    }

    public Review approveReview(String id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setApproved(true);
        return reviewRepository.save(review);
    }
    public long countApprovedReviews() {
        return reviewRepository.countByApprovedTrue();
    }
    public void deleteReview(String id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()) {
            reviewRepository.delete(review.get());
        } else {
            throw new NoSuchElementException("Review with id " + id + " does not exist");
        }
    }
}
