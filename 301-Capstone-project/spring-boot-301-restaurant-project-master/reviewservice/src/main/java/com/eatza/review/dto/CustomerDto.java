package com.eatza.review.dto;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor@AllArgsConstructor
public class CustomerDto {
	
	
	private Long customerId;
	private String customerName;
	private int age;
	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", customerName=" + customerName + ", age=" + age + "]";
	}
	
	

}
