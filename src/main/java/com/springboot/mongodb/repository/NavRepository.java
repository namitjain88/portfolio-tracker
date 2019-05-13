package com.springboot.mongodb.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.springboot.mongodb.document.Nav;

public interface NavRepository extends MongoRepository<Nav, Long> {

	@Query(value = "{ 'schemeCode' : ?0, 'navDate' : ?1}")
	List<Nav> findNavBySchemeCodeAndNavDate(Long schemeCode, LocalDate navDate);

	List<Nav> findNavBySchemeCode(Long schemeCode);

	Nav findFirstByOrderByNavDateDesc();
}
