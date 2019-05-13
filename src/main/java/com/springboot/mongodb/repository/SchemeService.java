package com.springboot.mongodb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.mongodb.document.Scheme;
import com.springboot.mongodb.utility.NavDataUtility;

@Component
public class SchemeService {

	@Autowired
	private SchemeRepository schemeRepository;

	@Autowired
	private NavDataUtility navDataUtility;

	public List<Scheme> getListOfSchemes() {
		return schemeRepository.findAll();
	}

	public void refreshSchemesInDb() {
		schemeRepository.deleteAll();
		schemeRepository.saveAll(navDataUtility.getListOfSchemes());
	}

	public Scheme getSchemeByCode(Long schemeCode) {
		return schemeRepository.findById(schemeCode).get();
	}
}
