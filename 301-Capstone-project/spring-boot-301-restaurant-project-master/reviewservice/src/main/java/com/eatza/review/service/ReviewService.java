package com.eatza.review.service;

import java.util.List;

import com.eatza.review.model.Review;

public interface ReviewService {
	public Review saveReview(Review review);

	public List<Review> getAllReviews();

	public Review getReviewById(Long reviewId);

	public String deleteReviewById(Long reviewId);
}
