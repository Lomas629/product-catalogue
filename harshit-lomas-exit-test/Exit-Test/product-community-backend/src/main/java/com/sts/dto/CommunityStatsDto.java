package com.sts.dto;

public class CommunityStatsDto {
	
	private Integer UserCount;
	private Integer ProductsCount;
	private Integer ReviewsCount;
	
	
	public Integer getUserCount() {
		return UserCount;
	}
	public void setUserCount(Integer userCount) {
		UserCount = userCount;
	}
	public Integer getProductsCount() {
		return ProductsCount;
	}
	public void setProductsCount(Integer productsCount) {
		ProductsCount = productsCount;
	}
	public Integer getReviewsCount() {
		return ReviewsCount;
	}
	public void setReviewsCount(Integer reviewsCount) {
		ReviewsCount = reviewsCount;
	}
	
	
	@Override
	public String toString() {
		return "CommunityStatsDto [UserCount=" + UserCount + ", ProductsCount=" + ProductsCount + ", ReviewsCount="
				+ ReviewsCount + "]";
	}
}
