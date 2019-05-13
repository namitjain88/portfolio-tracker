package com.springboot.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.mongodb.document.Portfolio;

public interface PortfolioRepository extends MongoRepository<Portfolio, Integer> {

	Portfolio findPortfolioByUserFirstName(String firstName);

	Object deletePortfolioByUserFirstName(String firstName);

	// List<Portfolio> findPortfolioByUserId(Integer userId);

}
