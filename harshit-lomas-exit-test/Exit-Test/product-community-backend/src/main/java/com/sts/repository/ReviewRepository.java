package com.sts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sts.entity.Reviews;

public interface ReviewRepository extends CrudRepository<Reviews, Integer>{

	public List<Reviews> findAllReviewsByProductId(Integer id);
}
