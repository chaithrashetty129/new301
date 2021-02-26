package com.eatza.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.eatza.review.model.Review;
import com.eatza.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Override
	public Review saveReview(Review review) {

		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReviews() {
		
		return reviewRepository.findAll();
	}

	@Override
	public Review getReviewById(Long reviewId) {
		return reviewRepository.findById(reviewId).get();
	}

	@Override
	public String deleteReviewById(Long reviewId) {
		reviewRepository.deleteById(reviewId);
		return "Deleted Successfully";

	}

	

}
