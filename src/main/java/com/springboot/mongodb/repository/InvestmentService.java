package com.springboot.mongodb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mongodb.document.Investment;
import com.springboot.mongodb.utility.NextSequenceService;

@Service
public class InvestmentService {

	@Autowired
	private InvestmentRepository investmentRepository;

	@Autowired
	private NextSequenceService nextSequenceService;

	public List<Investment> getAllInvestments() {
		return investmentRepository.findAll();
	}

	public List<Investment> getAllInvestmentsBySchemeCode(Long schemeCode) {
		return investmentRepository.findAllInvestmentsBySchemeCode(schemeCode);
	}

	public Object createInvestment(Investment investment) {
		investment.setId(nextSequenceService.getNextSequence("investmentSequence"));
		return investmentRepository.insert(investment);
	}

	public Object saveInvestment(Investment investment) {
		return investmentRepository.save(investment);
	}

	public void deleteInvestment(Investment investment) {
		investmentRepository.delete(investment);
	}

	public List<Investment> getAllInvestmentsByUserId(Integer userId) {
		return investmentRepository.findAllInvestmentsByUserId(userId);
	}

	public void deleteInvestmentsByUserId(Integer userId) {
		investmentRepository.deleteInvestmentsByUserId(userId);
	}

}
