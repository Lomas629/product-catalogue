package com.sts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.dto.SearchRequestDto;
import com.sts.entity.Product;
import com.sts.entity.Reviews;
import com.sts.exceptions.DuplicateProductIDException;
import com.sts.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository dao;
	
	@Autowired
	private ReviewSerivce reviewService;
	
	@Autowired
	private UtilService utilService;
	
	public Product addProduct(Product product) {
		Integer Id = product.getId();
		Optional<Product> productById = this.dao.findById(Id);
		
		if(productById.isPresent())
			throw new DuplicateProductIDException("Product with this product ID is already present !!");
		else
			return this.dao.save(product);
	}
	
	public List<Product> getAllProducts(){
		return (List<Product>) this.dao.findAll();
	}
	
	public Integer getCount() {
		return (int) this.dao.count();
	}
	
	
	public Product findProductById(Integer id) {
		Optional<Product> productById = this.dao.findById(id);
		return productById.get();
	}
	
	public List<Product> findProductByBrand(String brand){
		return (List<Product>) this.dao.findAllByBrand(brand);
	}
	
	public List<Product> findProductByName(String name) {
		return (List<Product>) this.dao.findAllByName(name);
	}
	
	
	public Map<Integer,List<Double>> getProductStats() {
		Map<Integer,List<Double>> productStats = new HashMap<>();
		for(Product product:getAllProducts()) {
			
			List<Double> reviewData= new ArrayList<>();
			Integer id=product.getId();
			List<Reviews> reviews=this.reviewService.getReviews(id);
			Double noOfreviews=utilService.getApprovedReviewByProduct(reviews);
			Double averageRating= utilService.getAverageRatingByProduct(reviews,noOfreviews);
			
			reviewData.add(noOfreviews);
			reviewData.add(averageRating);
			productStats.put(id, reviewData);
			
		}
		
		System.out.println(productStats);
		return productStats;
	}
	
	
	public List<Product> searchResults(SearchRequestDto searchRequest) {
		List<Product> products = new ArrayList<>();
		
		String name=searchRequest.getName();
		String brand=searchRequest.getBrand();
		Integer code=searchRequest.getId();
		
		if(code!=null) {
			products.add(findProductById(code));
		}
		if(brand != "") {
			for(Product product:findProductByBrand(brand)) {
				products.add(product);
			}
		}
		if(name != "") {
			for(Product product:findProductByName(name)) {
				products.add(product);
			}
		}
		
		return products;
		
	}

}
