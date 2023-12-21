package com.sts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.entity.User;
import com.sts.exceptions.EmailAlreadyExist;
import com.sts.exceptions.InvalidCredentialsException;
import com.sts.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository dao;
	
	public User addUser(User user) {	
		User user1 = this.dao.findByEmail(user.getEmail());
		if(user1 != null) 
			throw new EmailAlreadyExist("User with this email address already exist !!");	
		else
			return this.dao.save(user);
	}
	
	public Integer getCount() {
		return (int) this.dao.count();
	}
	
	public User findUser(String email,String password) {
		User user = this.dao.findByEmailAndPassword(email, password);
		if(user == null)
			throw new InvalidCredentialsException("Invalid Credentials");
		else
			return user;
	}

}
