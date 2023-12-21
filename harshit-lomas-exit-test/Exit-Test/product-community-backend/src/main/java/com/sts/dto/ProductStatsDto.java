package com.sts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatsDto {
	
	private Integer id;
	private Double noOfreviews;
	private Double averageRating;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getNoOfreviews() {
		return noOfreviews;
	}
	public void setNoOfreviews(Double noOfreviews) {
		this.noOfreviews = noOfreviews;
	}
	public Double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
	
	@Override
	public String toString() {
		return "ProductStatsDto [id=" + id + ", noOfreviews=" + noOfreviews + ", averageRating=" + averageRating + "]";
	}

}
