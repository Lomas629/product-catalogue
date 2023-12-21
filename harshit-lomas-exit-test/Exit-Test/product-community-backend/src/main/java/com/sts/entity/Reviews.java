package com.sts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class Reviews {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name = "rating",nullable = false)
	private Integer rating;
	
	@Column(name="customer",nullable = false)
	private String customer;
	
	@Lob
	@Column(name = "review", nullable = false,columnDefinition = "TEXT",length = 5000)
	private String review;
	
	@Column(name="isApproved",nullable=false)
	private boolean isApproved=false;
	
	@ManyToOne
	private Product product;

	
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Reviews [id=" + id + ", rating=" + rating + ", customer=" + customer + ", review=" + review
				+ ", isApproved=" + isApproved + "]";
	}

	
	

}
