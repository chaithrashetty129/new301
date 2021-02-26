package com.eatza.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eatza.review.dto.CustomerDto;
import com.eatza.review.model.Review;
import com.eatza.review.service.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	private KafkaTemplate<String, Review> kafkaTemplate;

	private static final String TOPIC = "ReviewKafka";
	
	RestTemplate restTemplate=new RestTemplate();
	
	
	

	@Autowired
	ReviewService reviewService;

	@GetMapping("/custupdatereview/{custId}/{rating}")
	public String custUpdateReview(@PathVariable("custId") Long customerId,@PathVariable("rating") int rating) {
		CustomerDto customerDto=restTemplate.getForObject("https://localhost:8083/getcustomerbyid/"+customerId, CustomerDto.class);
		System.out.println(customerDto);
		return "Success";
	}
	@PostMapping("/savereview")
	public Review saveReview(@RequestBody Review review) {
		kafkaTemplate.send(TOPIC, review);
		return reviewService.saveReview(review);
	}

	@GetMapping("/getallreviews")
	public List<Review> getAllReviews() {
		return reviewService.getAllReviews();
	}

	@GetMapping("/getreviewbyid/{id}")
	public Review getReviewById(@PathVariable("id") Long reviewId) {
		return reviewService.getReviewById(reviewId);
	}

	@GetMapping("/deletereviewbyid/{reviewId}")
	public String deleteReviewById(@PathVariable Long reviewId) {
		reviewService.deleteReviewById(reviewId);
		return "Successfully deleted";
	}
	
	
	

}
