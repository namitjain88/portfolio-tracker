package com.springboot.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.mongodb.document.Scheme;

public interface SchemeRepository extends MongoRepository<Scheme, Long> {

}
