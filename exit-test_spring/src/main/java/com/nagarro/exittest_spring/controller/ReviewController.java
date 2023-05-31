package com.nagarro.exittest_spring.controller;

import com.nagarro.exittest_spring.entity.Review;
import com.nagarro.exittest_spring.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/unapproved")
    public List<Review> getUnapprovedReviews() {
        return reviewService.getUnapprovedReviews();
    }

    @PostMapping("/add")
    public Review addReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

    @PatchMapping("/{id}/approve")
    public Review approveReview(@PathVariable String id) {
        return reviewService.approveReview(id);
    }
    @GetMapping("/count/approved")
    public ResponseEntity<Long> countApprovedReviews() {
        return new ResponseEntity<>(reviewService.countApprovedReviews(), HttpStatus.OK);
    }
 // In ReviewController.java

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build(); // Return HTTP 204 No Content on success
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Return HTTP 404 Not Found if review with the given id doesn't exist
        }
    }


}
