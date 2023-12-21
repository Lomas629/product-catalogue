package com.sts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.entity.Reviews;
import com.sts.repository.ReviewRepository;

@Service
public class ReviewSerivce {
	
	@Autowired
	private ReviewRepository dao;
	
	public Reviews addReview(Reviews review) {
		return this.dao.save(review);
	}
	
	public Integer getCount() {
		return (int) this.dao.count();
	}
	
	public List<Reviews> getReviews(Integer id){
		return (List<Reviews>) this.dao.findAllReviewsByProductId(id);
	}
	
	public Reviews approveReviewById(Integer id) {
		Optional<Reviews> reviewbyId = this.dao.findById(id);
		Reviews review = reviewbyId.get();
		review.setApproved(true);
		return this.dao.save(review);
	}
	
}
