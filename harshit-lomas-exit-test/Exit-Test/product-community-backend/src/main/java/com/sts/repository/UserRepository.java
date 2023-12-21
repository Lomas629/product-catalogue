package com.sts.repository;

import org.springframework.data.repository.CrudRepository;

import com.sts.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public User findByEmail(String email);
	
	public User findByEmailAndPassword(String email,String password);

}
