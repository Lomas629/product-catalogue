package com.sts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.dto.CommunityStatsDto;
import com.sts.dto.ProductStatsDto;
import com.sts.entity.Reviews;
import com.sts.repository.ProductRepository;
import com.sts.repository.ReviewRepository;
import com.sts.repository.UserRepository;

@Service
public class UtilService {
	
	public Double getAverageRatingByProduct(List<Reviews> reviews,double noOfreviews) {
		double avg = 0.0;
		if(noOfreviews == 0) return avg;
		int ratingSum = 0;
		for(Reviews review:reviews) {
			boolean checkApproval=review.isApproved();
			if(checkApproval==true) {
				ratingSum += review.getRating();
			}
		}
		
		if(ratingSum==0) return 0.0;
		
		avg = (ratingSum/noOfreviews);
		return avg;
	}
	
	public Double getApprovedReviewByProduct(List<Reviews> reviews) {
		double count=0;
		for(Reviews review:reviews) {
			boolean checkApproval=review.isApproved();
			if(checkApproval==true) {
				count++;
			}
		}
		return count;
	}

	public List<ProductStatsDto> toProductStatsDtoList(Map<Integer,List<Double>> statsData) {
		List<ProductStatsDto> statsList = new ArrayList<>();
		Set<Integer> IDs = statsData.keySet();
		
		for(Integer id:IDs) {
			List<Double> data=statsData.get(id);
			
			ProductStatsDto stats = new ProductStatsDto();
			stats.setId(id);
			stats.setNoOfreviews(data.get(0));
			stats.setAverageRating(data.get(1));
			
			statsList.add(stats);
		}
		
		return statsList;
	}
	
	public CommunityStatsDto toCommunityStatsDto(Integer productCount,Integer reviewCount,Integer userCount) {
		CommunityStatsDto communityStatsDto = new CommunityStatsDto();
		
		communityStatsDto.setProductsCount(productCount);
		communityStatsDto.setReviewsCount(reviewCount);
		communityStatsDto.setUserCount(userCount);
		
		return communityStatsDto;
	}
	
}
