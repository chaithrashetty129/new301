package com.eatza.restaurantsearch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ReviewDto {
	
	private Long reviewId;
	private Long customerId;
	private Long restaurantId ;
	private int rating;
	public ReviewDto(Long reviewId, Long customerId, Long restaurantId, int rating) {
		super();
		this.reviewId = reviewId;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "ReviewDto [reviewId=" + reviewId + ", customerId=" + customerId + ", restaurantId=" + restaurantId
				+ ", rating=" + rating + "]";
	}
	
	

}
