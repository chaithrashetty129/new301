package com.eatza.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.test.context.junit4.SpringRunner;

import com.eatza.review.model.Review;
import com.eatza.review.repository.ReviewRepository;
import com.eatza.review.service.ReviewServiceImpl;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureJson
@AutoConfigureJsonTesters
class ReviewServiceTest {

	@InjectMocks
	ReviewServiceImpl reviewServiceImpl;

	@Mock
	ReviewRepository reviewRepository;

	@Test
	public void saveReviewTest() {

		Review review = getReview();

		when(reviewRepository.save(review)).thenReturn(review);
		Review returnedReview = reviewServiceImpl.saveReview(review);

		assertNotNull(reviewServiceImpl.saveReview(review));
		assertEquals(review, returnedReview);

		assertEquals(1L, returnedReview.getReviewId());
		assertEquals(1L, returnedReview.getCustomerId());
		assertEquals(1L, returnedReview.getRestaurantId());
		assertEquals(4, returnedReview.getRating());

	}

	@Test
	public void getAllReviewsTest() {
		List<Review> reviews = getReviewList();

		when(reviewRepository.findAll()).thenReturn(reviews);
		assertNotNull(reviewServiceImpl.getAllReviews());
		assertEquals(1L, reviewServiceImpl.getAllReviews().get(0).getReviewId());
		assertEquals(1L, reviewServiceImpl.getAllReviews().get(0).getRestaurantId());
		assertEquals(1L, reviewServiceImpl.getAllReviews().get(0).getCustomerId());
		assertEquals(4, reviewServiceImpl.getAllReviews().get(0).getRating());

		assertEquals(2L, reviewServiceImpl.getAllReviews().get(1).getReviewId());
		assertEquals(2L, reviewServiceImpl.getAllReviews().get(1).getRestaurantId());
		assertEquals(2L, reviewServiceImpl.getAllReviews().get(1).getCustomerId());
		assertEquals(4, reviewServiceImpl.getAllReviews().get(1).getRating());

		assertEquals(3L, reviewServiceImpl.getAllReviews().get(2).getReviewId());
		assertEquals(3L, reviewServiceImpl.getAllReviews().get(2).getRestaurantId());
		assertEquals(3L, reviewServiceImpl.getAllReviews().get(2).getCustomerId());
		assertEquals(4, reviewServiceImpl.getAllReviews().get(2).getRating());

		assertEquals(4L, reviewServiceImpl.getAllReviews().get(3).getReviewId());
		assertEquals(4L, reviewServiceImpl.getAllReviews().get(3).getRestaurantId());
		assertEquals(4L, reviewServiceImpl.getAllReviews().get(3).getCustomerId());
		assertEquals(4, reviewServiceImpl.getAllReviews().get(3).getRating());

	}

	@Test
	public void getReviewByIdTest() {
		Long id = 1L;
		Review review = getReview();
		Optional<Review> checkReview = Optional.of(review);
		when(reviewRepository.findById(1L)).thenReturn(checkReview);
		assertNotNull(reviewServiceImpl.getReviewById(1L));
		assertEquals(id, reviewServiceImpl.getReviewById(1L).getReviewId());
		assertEquals(id, reviewServiceImpl.getReviewById(1L).getCustomerId());
		assertEquals(id, reviewServiceImpl.getReviewById(1L).getRestaurantId());
		assertEquals(4, reviewServiceImpl.getReviewById(1L).getRating());

	}

	@Test
	public void getReviewByIdTest1() {
		Long id = 1L;
		Review review = new Review();
		review.setReviewId(1L);
		review.setCustomerId(1L);
		review.setRestaurantId(1L);
		review.setRating(4);
		Optional<Review> checkReview = Optional.of(review);
		when(reviewRepository.findById(1L)).thenReturn(checkReview);
		assertNotNull(reviewServiceImpl.getReviewById(1L));
		assertEquals(id, reviewServiceImpl.getReviewById(1L).getReviewId());
		assertEquals(id, reviewServiceImpl.getReviewById(1L).getCustomerId());
		assertEquals(id, reviewServiceImpl.getReviewById(1L).getRestaurantId());
		assertEquals(4, reviewServiceImpl.getReviewById(1L).getRating());

	}

	@Test
	public void deleteReviewByIdTest1() {
		Long reviewId = 1L;
		reviewRepository.deleteById(reviewId);
		assertEquals("Deleted Successfully", reviewServiceImpl.deleteReviewById(reviewId));

	}

	public List<Review> getReviewList() {
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review(1L, 1L, 1L, 4));
		reviews.add(new Review(2L, 2L, 2L, 4));
		reviews.add(new Review(3L, 3L, 3L, 4));
		reviews.add(new Review(4L, 4L, 4L, 4));

		return reviews;
	}

	private Review getReview() {
		Review review = new Review();
		review.setReviewId(1L);
		review.setCustomerId(1L);
		review.setRestaurantId(1L);
		review.setRating(4);
		return review;
	}

}
