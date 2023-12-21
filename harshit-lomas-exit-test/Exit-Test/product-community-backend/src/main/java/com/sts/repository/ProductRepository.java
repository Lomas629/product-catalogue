package com.sts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sts.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	public List<Product> findAllByBrand(String brand);
	
	public List<Product> findAllByName(String name);
	
	

}
