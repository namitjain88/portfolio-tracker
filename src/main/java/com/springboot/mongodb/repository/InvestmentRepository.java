package com.springboot.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.mongodb.document.Investment;

public interface InvestmentRepository extends MongoRepository<Investment, Long> {

	List<Investment> findAllInvestmentsByUserId(Integer userId);

	List<Investment> findAllInvestmentsBySchemeCode(Long schemeCode);

	void deleteInvestmentsByUserId(Integer userId);

}
