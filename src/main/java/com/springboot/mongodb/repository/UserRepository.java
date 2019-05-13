package com.springboot.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.mongodb.document.User;

public interface UserRepository extends MongoRepository<User, Integer> {

	User findUserByFirstName(String firstName);

	// User getUserByFirstName(String firstName);
}
